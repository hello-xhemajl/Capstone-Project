package helo.mali.sneakerverse.signin;

import android.app.ActivityOptions;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import helo.mali.sneakerverse.BuildConfig;
import helo.mali.sneakerverse.R;
import helo.mali.sneakerverse.browse.BrowserActivity;
import helo.mali.sneakerverse.favorites.FavoritesActivity;
import helo.mali.sneakerverse.user.UserBuilder;

/**
 * Responsible for signing users in
 */
public class SigninActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 101;

    private FirebaseAuth auth;
    private SigninViewModel signinVm;

    @BindView(R.id.error_view)
    View errorView;

    @BindView(R.id.error_text_view)
    TextView errorTextView;

    @BindView(R.id.retry_button)
    Button retryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();
        signinVm = ViewModelProviders.of(this).get(SigninViewModel.class);

        tryToSignin();
    }

    private void tryToSignin() {
        // Is user signed in?
        if (auth.getCurrentUser() != null) {
            navigateToMainActivity();
        }
        // User not signed in. Offer user to sign in
        else {
            // Choose authentication providers
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.FacebookBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build());

            // Create and launch sign-in intent
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                onSuccessfulSignin();
            } else {
                onFailedSignin(response);
            }
        }
    }

    public void onSuccessfulSignin() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        signinVm.saveUser(UserBuilder.anUser()
                .withAvatarUri(user.getPhotoUrl() != null ? user.getPhotoUrl().toString() : null)
                .withName(user.getDisplayName())
                .withUserId(user.getUid())
                .build());

        navigateToMainActivity();
    }

    public void onFailedSignin(IdpResponse response) {
        // Sign in failed. If response is null the user canceled the
        // sign-in flow using the back button. Otherwise check
        // response.getError().getErrorCode() and handle the error.
        showErrorView(true);

        if (response == null) {
            errorTextView.setText(R.string.signin_canceled_message);
        }

        if (response != null) {
            switch (response.getError().getErrorCode()) {
                case ErrorCodes.NO_NETWORK:
                    errorTextView.setText(R.string.no_network_message);
                    break;
                case ErrorCodes.PROVIDER_ERROR:
                    errorTextView.setText(R.string.general_unknown_error);
                    break;
            }
        }
    }

    public void onRetry(View view) {
        showErrorView(false);
        tryToSignin();
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(this, BrowserActivity.class);

        startActivity(intent, Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ?
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                : null);

        finish();
    }

    private void showErrorView(boolean shouldShowErrorView) {
        errorView.setVisibility(shouldShowErrorView ? View.VISIBLE : View.GONE);
        if (shouldShowErrorView) {
            errorTextView.requestFocus();
        }
    }
}

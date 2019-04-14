package helo.mali.sneakerverse.user;

public class UserBuilder {
    private String userId;
    private String name;
    private String avatarUri;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
        return this;
    }

    public User build() {
        return new User(userId, name, avatarUri);
    }
}

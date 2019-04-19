package helo.mali.sneakerverse;

import android.content.Context;

import helo.mali.sneakerverse.sneakers.SneakersBuilder;
import helo.mali.sneakerverse.sneakers.SneakersRepository;

public class AppDatabasePopulator {
    private SneakersRepository sneakersRepository;

    public AppDatabasePopulator(Context context) {
        this.sneakersRepository = new SneakersRepository(context);
    }

    public void populateSneakers() {
        sneakersRepository.save(
                SneakersBuilder.sneakers()
                        .withDesignStory("Designed by Mali, one of tha famous hartbreakers of the 21st century, these snekears really have charachter")
                        .withImageUri("https://images.journeys.com/images/products/1_495414_ZM_WHITE.JPG")
                        .withImageContentDescription("of cream colour, with toes")
                        .withName("MALA")
                        .build(),

                SneakersBuilder.sneakers()
                        .withDesignStory("Designed by Tringa, one of tha famous hartbreakers of the 21st century, these snekears really have charachter")
                        .withImageUri("https://nb.scene7.com/is/image/NB/m997gy_nb_02_i?$pdpPictLG$")
                        .withImageContentDescription("blue gray, or whatever color that is")
                        .withName("TRINGA")
                        .build(),

                SneakersBuilder.sneakers()
                        .withDesignStory("Designed by Elsa, one of tha famous hartbreakers of the 21st century, these snekears really have charachter")
                        .withImageUri("https://nb.scene7.com/is/image/NB/wl574ujb_nb_02_i?$dw_pgp_hd$")
                        .withImageContentDescription("mainly white, with toes")
                        .withName("ELSA")
                        .build()
        );
    }
}

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
                        .withDesignStory("The Air Max shoe was originally designed by Tinker Hatfield, who started out working for Nike as an architect designing shops, but the latest 720 series was spearheaded by Dylan Raasch and Jesi Small. They designed the sneakers specifically for the athlete of the streets – those who hustle and grind and stay on their feet for up to sixteen hours a day.\n" + "\n" + "For the 720 model, the design team pulled in from various sources of information to create a shoe built for a generation always on the go. They met with consumers, particularly from the younger demographic, to discuss various topics from what a typical day in the grind consists of to the physical effects of absorbing the daily barrage of noise from social and political turbulence. It was all summarized in one word: Drained. The purpose of this focus group was to not only deliver a product that fills consumers needs, but one that communicates comfort and support in more ways than one. They specifically pointed to the idea of conservation, shift, and exchange of energy, and implemented those transactions onto a shoe that mirrors emotion.")
                        .withImageUri("https://www.businessinsider.in/thumb/msid-68583962,width-640,resizemode-4/Mens-Air-Max-720.jpg?980740")
                        .withImageContentDescription("Futuristic, blue and latest technology life style sneakers")
                        .withName("Air Max 720 Team")
                        .build(),

                SneakersBuilder.sneakers()
                        .withDesignStory("The adidas Yeezy line is a line of footwear created by Kanye West and adidas. After leaving a contract with Nike in 2013, West then partnered with adidas and released his first shoe — the adidas Yeezy Boost 750 — in February of 2015, followed by the original adidas Yeezy 350 in June of 2015. Quickly becoming one of the most desired lines on the market thanks to its limited quantities and West's A-list celebrity status, the line then expanded with models like the Yeezy Boost 350 V2 in 2016, the Yeezy Boost 700 in 2017, and the Yeezy 500 in 2018.")
                        .withImageUri("https://i.ebayimg.com/images/g/lcIAAOSw0YVcSlxR/s-l640.jpg")
                        .withImageContentDescription("Colourful, unique and comfortable sneakers")
                        .withName("Adidas Yeezy 700")
                        .build(),

                SneakersBuilder.sneakers()
                        .withDesignStory("The Nike Kyrie 5 is Kyrie Irving's fifth signature shoe with Nike. Designed by Ben Nethongkome, it was officially unveiled in November, 2018 and has since released in several different styles. The Kyrie 5's most noticeable design feature is the flytrap-like system over the laces. The Kyrie 5 is one of the most popular signature basketball shoes on the market right now, and will release in several more styles as the 2018-2019 NBA season continues. Read the articles below to learn more about the Nike Kyrie 5.")
                        .withImageUri("https://2.kixify.com/sites/default/files/imagecache/product_full/product/2019/03/26/p_26064241_142658561_7989831.jpg")
                        .withImageContentDescription("Affortable, performance basketball sneakers")
                        .withName("Nike Kyrie 5")
                        .build(),

                SneakersBuilder.sneakers()
                        .withDesignStory("The adidas Nite Jogger is a shoe that’s all about being seen. Designed for nocturnal activity and featuring a look that’s highly visible — both in its overall style and its reflective accents — it’s one of adidas Originals‘ most compelling silhouettes currently on the market.")
                        .withImageUri("http://solemovement.com/wp-content/uploads/2019/01/nite_cov-640x480.jpg")
                        .withImageContentDescription("Soft, good looking sneakers")
                        .withName("Adidas Nite Jogger")
                        .build(),

                SneakersBuilder.sneakers()
                        .withDesignStory("The adidas Yeezy line is a line of footwear created by Kanye West and adidas. After leaving a contract with Nike in 2013, West then partnered with adidas and released his first shoe — the adidas Yeezy Boost 750 — in February of 2015, followed by the original adidas Yeezy 350 in June of 2015. Quickly becoming one of the most desired lines on the market thanks to its limited quantities and West's A-list celebrity status, the line then expanded with models like the Yeezy Boost 350 V2 in 2016, the Yeezy Boost 700 in 2017, and the Yeezy 500 in 2018.")
                        .withImageUri("https://media.karousell.com/media/photos/products/2019/01/30/adidas_yeezy_boost_350_v2_triple_white_1548843571_f640650a.jpg")
                        .withImageContentDescription("Light, running but also lifestyle sneakers")
                        .withName("Adidas Yeezy 350")
                        .build(),

                SneakersBuilder.sneakers()
                        .withDesignStory("Designed by Tony Alva and Stacy Peralta, Old Skool was the first Vans shoe to incorporate leather panels, and one of the first to feature the now-iconic “jazz stripe.” A popular silhouette for collaborations, everyone from Supreme to Tyler, The Creator to Concepts has added their personal touch to the shoe.")
                        .withImageUri("https://media.karousell.com/media/photos/products/2018/08/04/vans_old_skool_black_white_1533387346_082e497d.jpg")
                        .withImageContentDescription("Popular classic skate shoe ")
                        .withName("Vans Old Skool")
                        .build(),

                SneakersBuilder.sneakers()
                        .withDesignStory("The Nike React Element 87 is a hybrid lifestyle/running silhouette that released June 21st in Europe and later on July 13th release in the US. The shoe features a REACT midsole with rubber rivets drilled in for extra stability and a semi-translucent TPE upper with exposed components for extra flair. This sneaker is considered as one of the best new shoes of 2018, and two colorways are already confirmed to release in January of 2019. Read the articles below to learn more about the Nike React Element 87.")
                        .withImageUri("http://solemovement.com/wp-content/uploads/2019/01/el55red_cov-640x480.jpg")
                        .withImageContentDescription("Comfortable, translucent shoe ")
                        .withName("Nike React 55")
                        .build(),

                SneakersBuilder.sneakers()
                        .withDesignStory("It all starts here. A legendary franchise, as both a high performance footwear line and as cultural icon, is launched – the Air Jordan 1. So revolutionary for its day, they are banned by the NBA for violating its “uniformity of uniform” rule. Jordan wears them anyway, incurring a $5,000 fine per game. Likewise, MJ unveils his revolutionary style of play to the league and world at large, making his first All-Star game appearance, earning Rookie of the Year honours and inspiring Boston Celtics legend Larry Bird to observe one performance as “God disguised as Michael Jordan.” \n" + "\n" + "The shoes were designed for Nike by Peter Moore, Tinker Hatfield, and Bruce Kilgore.")
                        .withImageUri("http://www.ragusavacanze.it/images/product/ew-Nike-Air-Jordan-1-High-Scarpe-In-Nero-Bianco-726.jpg")
                        .withImageContentDescription("Iconic basketball shoe")
                        .withName("Nike Air Jordan 1")
                        .build(),

                SneakersBuilder.sneakers()
                        .withDesignStory("The adidas Ultra Boost is a popular running sneaker from adidas. Originally debuting in February 2015, the Ultra Boost has since become one of adidas's most popular shoes, finding a home with both style-conscious sneakerheads and runners who demand exemplary performance due to its comfortable and stylish combo of a Primeknit upper and Boost midsole.")
                        .withImageUri("https://i.ebayimg.com/images/g/r0MAAOSwJjZa3kq9/s-l640.jpg")
                        .withImageContentDescription("Running and lifestyle shoe")
                        .withName("Adidas Ultraboost")
                        .build(),

                SneakersBuilder.sneakers()
                        .withDesignStory("NIKE AIR FORCE 1 LOW The Nike Air Force 1 Low released in 1982 and was designed by Bruce Kilgore. It is the first Nike Basketball sneaker with Air technology, and also comes in a Mid and High version. The Air Force 1 Low is considered to be the most heralded sneaker of all-time, consistently being among the best sellers of the year — especially in its classic all-white colorway")
                        .withImageUri("https://media.karousell.com/media/photos/products/2019/01/24/nike_air_force_low_nikeairforce_1548293920_3a6a9368.jpg")
                        .withImageContentDescription("Classic, all-day wearable shoe")
                        .withName("Nike Air Force Low")
                        .build()
        );
    }
}

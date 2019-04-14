package helo.mali.sneakerverse.sneakers;

public class SneakersBuilder {
    private String imageUri;
    private String name;
    private String designStory;

    private SneakersBuilder() {
    }

    public static SneakersBuilder sneakers() {
        return new SneakersBuilder();
    }

    public SneakersBuilder withImageUri(String imageUri) {
        this.imageUri = imageUri;
        return this;
    }

    public SneakersBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SneakersBuilder withDesignStory(String designStory) {
        this.designStory = designStory;
        return this;
    }

    public Sneakers build() {
        Sneakers sneakers = new Sneakers();
        sneakers.setImageUri(imageUri);
        sneakers.setName(name);
        sneakers.setDesignStory(designStory);
        return sneakers;
    }
}

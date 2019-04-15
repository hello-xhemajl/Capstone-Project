package helo.mali.sneakerverse.usersneakers;

public class UserSneakersBuilder {
    private Long id;
    private String userId;
    private Long sneakersId;

    private UserSneakersBuilder() {
    }

    public static UserSneakersBuilder anUserSneakersTuple() {
        return new UserSneakersBuilder();
    }

    public UserSneakersBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserSneakersBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserSneakersBuilder withSneakersId(Long sneakersId) {
        this.sneakersId = sneakersId;
        return this;
    }

    public UserSneakers build() {
        UserSneakers userSneakers = new UserSneakers();
        userSneakers.setId(id);
        userSneakers.setUserId(userId);
        userSneakers.setSneakersId(sneakersId);
        return userSneakers;
    }
}

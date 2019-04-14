package helo.mali.sneakerverse.sneakers;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Sneakers {
    @ColumnInfo(name = "image_uri")
    private String imageUri;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "design_story")
    private String designStory;

    @PrimaryKey(autoGenerate = true)
    private Long sneakersId;


    public Long getSneakersId() {
        return sneakersId;
    }

    public void setSneakersId(Long sneakersId) {
        this.sneakersId = sneakersId;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignStory() {
        return designStory;
    }

    public void setDesignStory(String designStory) {
        this.designStory = designStory;
    }
}

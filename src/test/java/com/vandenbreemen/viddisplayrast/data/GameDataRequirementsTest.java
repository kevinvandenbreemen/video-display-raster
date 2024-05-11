package com.vandenbreemen.viddisplayrast.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameDataRequirementsTest {

    @Test
    public void shouldRetrieveSprite() {
        GameDataRequirements requirements = new GameDataRequirements(100, 100, 4, 4, 1024);
        byte[] data = new byte[]{
           0, 1, 1, 3,
           2, 3, 3, 0,
           0, 1, 1, 3,
           2, 3, 3, 0
        };
        requirements.setData(3, data);

        byte[] sprite = requirements.getSpriteData(3);
        assertArrayEquals(data, sprite, "Sprite data should be the same");
    }

    @Test
    public void shouldPreventRetrievalOfSpritesFromInvalidLocations() {
        GameDataRequirements requirements = new GameDataRequirements(100, 100, 4, 4, 32);
        byte[] data = new byte[]{
           0, 1, 1, 3,
           2, 3, 3, 0,
           0, 1, 1, 3,
           2, 3, 3, 0
        };
        requirements.setData(1, data);

        assertThrows(
            IllegalArgumentException.class,
            () -> requirements.getSpriteData(2),
            "Should not allow retrieval of sprite data from invalid location"
        );

    }

    @Test
    public void shouldNotAllowSettingSpriteDataToInvalidLocation() {
        GameDataRequirements requirements = new GameDataRequirements(100, 100, 4, 4, 32);
        byte[] data = new byte[]{
           0, 1, 1, 3,
           2, 3, 3, 0,
           0, 1, 1, 3,
           2, 3, 3, 0
        };

        assertThrows(
            IllegalArgumentException.class,
            () -> requirements.setData(4, data),
            "Should not allow setting data to invalid location"
        );
    }

}
package com.tayjay.fullpower.util;

import com.google.common.base.Preconditions;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;

import java.lang.ref.WeakReference;
import java.util.UUID;

/**
 * Created by tayjm_000 on 2015-11-23.
 */
// Leveraged from the BuildCraft source code.
public final class FakePlayerHelper {

    private FakePlayerHelper() {}

    private static GameProfile profile = null;
    private static WeakReference<EntityPlayer> fakePlayer = new WeakReference<EntityPlayer>(
            null);

    public static void initialize(final GameProfile profile) {
        Preconditions.checkNotNull(profile, "GameProfile cannot be null");
        FakePlayerHelper.profile = profile;
    }

    public static void initialize(final String name) {
        Preconditions.checkNotNull(name, "String (name) cannot be null");

        FakePlayerHelper.profile = new GameProfile(UUID.nameUUIDFromBytes(name
                .getBytes()), "[" + name + "]");
    }

    public static final GameProfile getProfile() {
        return profile;
    }

    public static final UUID getFakePlayerID() {
        assert profile != null;
        return profile.getId();
    }

    private static WeakReference<EntityPlayer> createNewPlayer(final WorldServer world) {
        final EntityPlayer player = FakePlayerFactory.get(world, profile);
        return new WeakReference<EntityPlayer>(player);
    }

    private static WeakReference<EntityPlayer> createNewPlayer(
            final WorldServer world, final int x, final int y, final int z) {
        final EntityPlayer player = FakePlayerFactory.get(world, profile);
        player.posX = x;
        player.posY = y;
        player.posZ = z;
        return new WeakReference<EntityPlayer>(player);
    }

    public final static WeakReference<EntityPlayer> getFakePlayer(
            final WorldServer world) {

        Preconditions.checkNotNull(world, "WorldServer cannot be null");
        Preconditions.checkNotNull(profile, "GameProfile not initialized");

        if (fakePlayer.get() == null) {
            fakePlayer = createNewPlayer(world);
        } else {
            fakePlayer.get().worldObj = world;
        }

        return fakePlayer;
    }

    public final static WeakReference<EntityPlayer> getFakePlayer(
            final WorldServer world, final int x, final int y, final int z) {

        Preconditions.checkNotNull(world, "WorldServer cannot be null");
        Preconditions.checkNotNull(profile, "GameProfile not initialized");

        if (fakePlayer.get() == null) {
            fakePlayer = createNewPlayer(world, x, y, z);
        } else {
            fakePlayer.get().worldObj = world;
            fakePlayer.get().posX = x;
            fakePlayer.get().posY = y;
            fakePlayer.get().posZ = z;
        }

        return fakePlayer;
    }
}


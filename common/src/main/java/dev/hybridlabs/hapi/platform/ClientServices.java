package dev.hybridlabs.hapi.platform;

import dev.hybridlabs.hapi.platform.services.ClientPlatformHelper;

import static dev.hybridlabs.hapi.platform.Services.load;

public class ClientServices {
    public static final ClientPlatformHelper PLATFORM = load(ClientPlatformHelper.class);
}

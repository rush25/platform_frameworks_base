/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.media;

import android.util.SparseIntArray;

/**
 * Class to provide information about the audio devices.
 * @hide
 */
public class AudioDevice {

    /**
     * A device type associated with an unknown or uninitialized device.
     */
    public static final int TYPE_UNKNOWN          = 0;
    /**
     * A device type describing the attached earphone speaker.
     */
    public static final int TYPE_BUILTIN_EARPIECE = 1;
    /**
     * A device type describing the speaker system (i.e. a mono speaker or stereo speakers) built
     * in a device.
     */
    public static final int TYPE_BUILTIN_SPEAKER  = 2;
    /**
     * A device type describing a headset, which is the combination of a headphones and microphone.
     */
    public static final int TYPE_WIRED_HEADSET    = 3;
    /**
     * A device type describing a pair of wired headphones .
     */
    public static final int TYPE_WIRED_HEADPHONES = 4;
    /**
     * A device type describing an analog line-level connection.
     */
    public static final int TYPE_LINE_ANALOG      = 5;
    /**
     * A device type describing a digital line connection (e.g. SPDIF).
     */
    public static final int TYPE_LINE_DIGITAL     = 6;
    /**
     * A device type describing a Bluetooth device typically used for telephony .
     */
    public static final int TYPE_BLUETOOTH_SCO    = 7;
    /**
     * A device type describing a Bluetooth device supporting the A2DP profile.
     */
    public static final int TYPE_BLUETOOTH_A2DP   = 8;
    /**
     * A device type describing an HDMI connection .
     */
    public static final int TYPE_HDMI             = 9;
    /**
     * A device type describing the Audio Return Channel of an HDMI connection.
     */
    public static final int TYPE_HDMI_ARC         = 10;
    /**
     * A device type describing a USB audio device.
     */
    public static final int TYPE_USB_DEVICE       = 11;
    /**
     * A device type describing a USB audio device in accessory mode.
     */
    public static final int TYPE_USB_ACCESSORY    = 12;
    /**
     * A device type describing the audio device associated with a dock.
     */
    public static final int TYPE_DOCK             = 13;
    /**
     * A device type associated with the transmission of audio signals over FM.
     */
    public static final int TYPE_FM               = 14;
    /**
     * A device type describing the microphone(s) built in a device.
     */
    public static final int TYPE_BUILTIN_MIC      = 15;
    /**
     * A device type for accessing the audio content transmitted over FM.
     */
    public static final int TYPE_FM_TUNER         = 16;
    /**
     * A device type for accessing the audio content transmitted over the TV tuner system.
     */
    public static final int TYPE_TV_TUNER         = 17;
    /**
     * A device type describing the transmission of audio signals over the telephony network.
     */
    public static final int TYPE_TELEPHONY        = 18;
    /**
     * A device type describing the auxiliary line-level connectors.
     */
    public static final int TYPE_AUX_LINE         = 19;

    AudioDevicePortConfig mConfig;

    AudioDevice(AudioDevicePortConfig config) {
        mConfig = new AudioDevicePortConfig(config);
    }

    /**
     * @hide
     * CANDIDATE FOR PUBLIC API
     * @return
     */
    public boolean isInputDevice() {
        return (mConfig.port().role() == AudioPort.ROLE_SOURCE);
    }

    /**
     * @hide
     * CANDIDATE FOR PUBLIC API
     * @return
     */
    public boolean isOutputDevice() {
        return (mConfig.port().role() == AudioPort.ROLE_SINK);
    }

    /**
     * @hide
     * CANDIDATE FOR PUBLIC API
     * @return
     */
    public int getDeviceType() {
        return INT_TO_EXT_DEVICE_MAPPING.get(mConfig.port().type(), TYPE_UNKNOWN);
    }

    /**
     * @hide
     * CANDIDATE FOR PUBLIC API
     * @return
     */
    public String getAddress() {
        return mConfig.port().address();
    }

    /** @hide */
    public static int convertDeviceTypeToInternalDevice(int deviceType) {
        return EXT_TO_INT_DEVICE_MAPPING.get(deviceType, AudioSystem.DEVICE_NONE);
    }

    /** @hide */
    public static int convertInternalDeviceToDeviceType(int intDevice) {
        return INT_TO_EXT_DEVICE_MAPPING.get(intDevice, TYPE_UNKNOWN);
    }

    private static final SparseIntArray INT_TO_EXT_DEVICE_MAPPING;

    private static final SparseIntArray EXT_TO_INT_DEVICE_MAPPING;

    static {
        INT_TO_EXT_DEVICE_MAPPING = new SparseIntArray();
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_EARPIECE, TYPE_BUILTIN_EARPIECE);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_SPEAKER, TYPE_BUILTIN_SPEAKER);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_WIRED_HEADSET, TYPE_WIRED_HEADSET);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_WIRED_HEADPHONE, TYPE_WIRED_HEADPHONES);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_BLUETOOTH_SCO, TYPE_BLUETOOTH_SCO);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_BLUETOOTH_SCO_HEADSET, TYPE_BLUETOOTH_SCO);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_BLUETOOTH_SCO_CARKIT, TYPE_BLUETOOTH_SCO);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_BLUETOOTH_A2DP, TYPE_BLUETOOTH_A2DP);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_BLUETOOTH_A2DP_HEADPHONES, TYPE_BLUETOOTH_A2DP);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_BLUETOOTH_A2DP_SPEAKER, TYPE_BLUETOOTH_A2DP);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_HDMI, TYPE_HDMI);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_ANLG_DOCK_HEADSET, TYPE_DOCK);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_DGTL_DOCK_HEADSET, TYPE_DOCK);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_USB_ACCESSORY, TYPE_USB_ACCESSORY);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_USB_DEVICE, TYPE_USB_DEVICE);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_TELEPHONY_TX, TYPE_TELEPHONY);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_LINE, TYPE_LINE_ANALOG);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_HDMI_ARC, TYPE_HDMI_ARC);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_SPDIF, TYPE_LINE_DIGITAL);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_FM, TYPE_FM);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_OUT_AUX_LINE, TYPE_AUX_LINE);

        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_BUILTIN_MIC, TYPE_BUILTIN_MIC);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_BLUETOOTH_SCO_HEADSET, TYPE_BLUETOOTH_SCO);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_WIRED_HEADSET, TYPE_WIRED_HEADSET);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_HDMI, TYPE_HDMI);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_TELEPHONY_RX, TYPE_TELEPHONY);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_BACK_MIC, TYPE_BUILTIN_MIC);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_ANLG_DOCK_HEADSET, TYPE_DOCK);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_DGTL_DOCK_HEADSET, TYPE_DOCK);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_USB_ACCESSORY, TYPE_USB_ACCESSORY);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_USB_DEVICE, TYPE_USB_DEVICE);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_FM_TUNER, TYPE_FM_TUNER);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_TV_TUNER, TYPE_TV_TUNER);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_LINE, TYPE_LINE_ANALOG);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_SPDIF, TYPE_LINE_DIGITAL);
        INT_TO_EXT_DEVICE_MAPPING.put(AudioSystem.DEVICE_IN_BLUETOOTH_A2DP, TYPE_BLUETOOTH_A2DP);

        // not covered here, legacy
        //AudioSystem.DEVICE_OUT_REMOTE_SUBMIX
        //AudioSystem.DEVICE_IN_REMOTE_SUBMIX

        // privileges mapping to output device
        EXT_TO_INT_DEVICE_MAPPING = new SparseIntArray();
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_BUILTIN_EARPIECE, AudioSystem.DEVICE_OUT_EARPIECE);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_BUILTIN_SPEAKER, AudioSystem.DEVICE_OUT_SPEAKER);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_WIRED_HEADSET, AudioSystem.DEVICE_OUT_WIRED_HEADSET);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_WIRED_HEADPHONES, AudioSystem.DEVICE_OUT_WIRED_HEADPHONE);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_LINE_ANALOG, AudioSystem.DEVICE_OUT_LINE);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_LINE_DIGITAL, AudioSystem.DEVICE_OUT_SPDIF);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_BLUETOOTH_SCO, AudioSystem.DEVICE_OUT_BLUETOOTH_SCO);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_BLUETOOTH_A2DP, AudioSystem.DEVICE_OUT_BLUETOOTH_A2DP);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_HDMI, AudioSystem.DEVICE_OUT_HDMI);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_HDMI_ARC, AudioSystem.DEVICE_OUT_HDMI_ARC);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_USB_DEVICE, AudioSystem.DEVICE_OUT_USB_DEVICE);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_USB_ACCESSORY, AudioSystem.DEVICE_OUT_USB_ACCESSORY);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_DOCK, AudioSystem.DEVICE_OUT_ANLG_DOCK_HEADSET);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_FM, AudioSystem.DEVICE_OUT_FM);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_BUILTIN_MIC, AudioSystem.DEVICE_IN_BUILTIN_MIC);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_FM_TUNER, AudioSystem.DEVICE_IN_FM_TUNER);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_TV_TUNER, AudioSystem.DEVICE_IN_TV_TUNER);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_TELEPHONY, AudioSystem.DEVICE_OUT_TELEPHONY_TX);
        EXT_TO_INT_DEVICE_MAPPING.put(TYPE_AUX_LINE, AudioSystem.DEVICE_OUT_AUX_LINE);
    }
}


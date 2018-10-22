
package io.github.johnjcool.dvblink.live.tv.tv.service;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.android.media.tv.companionlibrary.BaseTvInputService;
import com.google.android.media.tv.companionlibrary.model.Channel;
import com.google.android.media.tv.companionlibrary.model.Program;
import com.google.android.media.tv.companionlibrary.model.RecordedProgram;
import com.google.android.media.tv.companionlibrary.utils.TvContractUtils;

import io.github.johnjcool.dvblink.live.tv.tv.TvUtils;

class RecordingSession extends BaseTvInputService.RecordingSession {

    private static final boolean DEBUG = true;
    private static final String TAG = RecordingSession.class.getSimpleName();
    private Channel mChannel;
    private Uri mChannelUri;
    private Context mContext;
    private String mInputId;
    private int mRecordId;
    private String mVBoxChannelId;

    public RecordingSession(Context context, String s) {
        super(context, s);
        mInputId = s;
        mContext = context;
    }

//    private void createRecordedChannel(final Channel channelToRecord) {
//        VBoxApp.getDeviceManager().getRecordingStatus(mRecordId, new com.vboxcomm.android.tvinput.vbox.response.Callback.Get() {
//
//            final RecordingSession this$0;
//            final Channel val$channelToRecord;
//
//            public void onResult(Status status, RecordingStatus recordingstatus) {
//                if (status != null) {
//                    notifyError(0);
//                    Log.d(RecordingSession.TAG, (new StringBuilder()).append("createRecordedChannel: ,onResult: ").append(status.getDescription()).toString());
//                    return;
//                }
//                long l = VBoxUtils.getTimeInMillisFromVBoxTime(recordingstatus.startTime);
//                long l1 = VBoxUtils.getTimeInMillisFromVBoxTime(recordingstatus.endTime);
//                InternalProviderData internalproviderdata = channelToRecord.getInternalProviderData();
//                internalproviderdata.setVideoUrl(recordingstatus.url);
//                internalproviderdata.setRecordingStartTime(l);
//                try {
//                    internalproviderdata.put("vbox_record_id", Integer.valueOf(mRecordId));
//                    internalproviderdata.put("vbox_record_size", Long.valueOf(recordingstatus.fileSize));
//                }
//                // Misplaced declaration of an exception variable
//                catch (Status status) {
//                    Log.w(RecordingSession.TAG, (new StringBuilder()).append("onRecord: unable to set recording id: ").append(mRecordId).toString(), status);
//                }
//                status = null;
//                if (!TextUtils.isEmpty(channelToRecord.getChannelLogo())) {
//                    status = TvContract.buildChannelLogoUri(channelToRecord.getId()).toString();
//                }
//                status = (new com.google.android.media.tv.companionlibrary.model.RecordedProgram.Builder()).setInputId(mInputId).setRecordingDataUri(recordingstatus.url).setRecordingDurationMillis(l1 - l).setStartTimeUtcMillis(l).setEndTimeUtcMillis(l1).setInternalProviderData(internalproviderdata).setTitle(mChannel.getDisplayName()).setThumbnailUri(status).build();
//                notifyRecordingStopped(status);
//            }
//
//            public volatile void onResult(Status status, Object obj) {
//                onResult(status, (RecordingStatus) obj);
//            }
//
//
//            {
//                this$0 = RecordingSession.this;
//                channelToRecord = channel;
//                super();
//            }
//        });
//    }
//
//    private void createRecordedProgram(final Program programToRecord) {
//        VBoxApp.getDeviceManager().getRecordingStatus(mRecordId, new com.vboxcomm.android.tvinput.vbox.response.Callback.Get() {
//
//            final RecordingSession this$0;
//            final Program val$programToRecord;
//
//            public void onResult(Status status, RecordingStatus recordingstatus) {
//                if (status != null) {
//                    notifyError(0);
//                    Log.d(RecordingSession.TAG, (new StringBuilder()).append("createRecordedProgram, onResult: ").append(status.getDescription()).toString());
//                    return;
//                }
//                long l = VBoxUtils.getTimeInMillisFromVBoxTime(recordingstatus.startTime);
//                long l1 = VBoxUtils.getTimeInMillisFromVBoxTime(recordingstatus.endTime);
//                status = programToRecord.getInternalProviderData();
//                status.setVideoUrl(recordingstatus.url);
//                status.setRecordingStartTime(l);
//                try {
//                    status.put("vbox_record_id", Integer.valueOf(mRecordId));
//                    status.put("vbox_record_size", Long.valueOf(recordingstatus.fileSize));
//                } catch (com.google.android.media.tv.companionlibrary.model.InternalProviderData.ParseException parseexception) {
//                    Log.w(RecordingSession.TAG, (new StringBuilder()).append("onRecord: unable to set recording id: ").append(mRecordId).toString(), parseexception);
//                }
//                status = (new com.google.android.media.tv.companionlibrary.model.RecordedProgram.Builder(programToRecord)).setInputId(mInputId).setRecordingDataUri(recordingstatus.url).setRecordingDurationMillis(l1 - l).setStartTimeUtcMillis(l).setEndTimeUtcMillis(l1).setInternalProviderData(status).build();
//                notifyRecordingStopped(status);
//            }
//
//            public volatile void onResult(Status status, Object obj) {
//                onResult(status, (RecordingStatus) obj);
//            }
//
//
//            {
//                this$0 = RecordingSession.this;
//                programToRecord = program;
//                super();
//            }
//        });
//    }
//
//    private void startChannelRecording() {
//        VBoxApp.getDeviceManager().startRecording(mVBoxChannelId, new com.vboxcomm.android.tvinput.vbox.response.Callback.Get() {
//
//            final RecordingSession this$0;
//
//            public void onResult(Status status, com.vboxcomm.android.tvinput.vbox.response.RecordResponse.RecordId recordid) {
//                if (status != null) {
//                    notifyError(0);
//                    Log.d(RecordingSession.TAG, (new StringBuilder()).append("startChannelRecording: ,onResult: ").append(status.getDescription()).toString());
//                    return;
//                } else {
//                    mRecordId = recordid.recordId;
//                    return;
//                }
//            }
//
//            public volatile void onResult(Status status, Object obj) {
//                onResult(status, (com.vboxcomm.android.tvinput.vbox.response.RecordResponse.RecordId) obj);
//            }
//
//
//            {
//                this$0 = RecordingSession.this;
//                super();
//            }
//        });
//    }
//
//    private void startProgramRecording(Program program) {
//        String s = VBoxUtils.getVBoxXmlTvTime(program.getStartTimeUtcMillis());
//        String s1 = Uri.decode(program.getTitle());
//        Log.d(TAG, (new StringBuilder()).append("onStartRecording: ").append(mVBoxChannelId).append(", program title: ").append(program.getTitle()).append(", startTime: ").toString());
//        VBoxApp.getDeviceManager().startRecordingProgram(mVBoxChannelId, s1, s, new com.vboxcomm.android.tvinput.vbox.response.Callback.Get() {
//
//            final RecordingSession this$0;
//
//            public void onResult(Status status, com.vboxcomm.android.tvinput.vbox.response.RecordResponse.RecordId recordid) {
//                if (status != null) {
//                    notifyError(0);
//                    Log.d(RecordingSession.TAG, (new StringBuilder()).append("startChannelRecording: ,onResult: ").append(status.getDescription()).toString());
//                    return;
//                } else {
//                    mRecordId = recordid.recordId;
//                    return;
//                }
//            }
//
//            public volatile void onResult(Status status, Object obj) {
//                onResult(status, (com.vboxcomm.android.tvinput.vbox.response.RecordResponse.RecordId) obj);
//            }
//
//
//            {
//                this$0 = RecordingSession.this;
//                super();
//            }
//        });
//    }

    public void notifyRecordingStopped(RecordedProgram recordedprogram) {
        notifyRecordingStopped(mContext.getContentResolver().insert(android.media.tv.TvContract.RecordedPrograms.CONTENT_URI, recordedprogram.toContentValues()));
    }

    public void onRelease() {
        Log.d(TAG, "onRelease");
    }

/*
    public void onStartRecording(Uri uri) {
        super.onStartRecording(uri);
        Log.d(TAG, (new StringBuilder()).append("onStartRecording: ").append(uri).toString());
        mChannel = TvUtils.getChannel(mContext, mChannelUri);
        mVBoxChannelId = VBoxUtils.getVBoxChannelId(mChannel);
        if (uri != null) {
            uri = TvContractUtils.getProgram(mContext.getContentResolver(), mChannelUri, uri);
            if (uri == null) {
                notifyError(0);
                return;
            } else {
                startProgramRecording(uri);
                return;
            }
        } else {
            startChannelRecording();
            return;
        }
    }
*/

/*
    public void onStopRecording(final Program programToRecord) {
        Log.d(TAG, "onStopRecording");
        VBoxApp.getDeviceManager().stopRecording(mVBoxChannelId, new com.vboxcomm.android.tvinput.vbox.response.Callback.Get() {

            final RecordingSession this$0;
            final Program val$programToRecord;

            public void onResult(Status status, com.vboxcomm.android.tvinput.vbox.response.RecordResponse.RecordId recordid) {
                if (status == null) {
                    createRecordedProgram(programToRecord);
                    return;
                } else {
                    notifyError(0);
                    Log.d(RecordingSession.TAG, (new StringBuilder()).append("onStopRecording, recordId: ").append(mVBoxChannelId).append(", ").append(status.getDescription()).toString());
                    return;
                }
            }

            public volatile void onResult(Status status, Object obj) {
                onResult(status, (com.vboxcomm.android.tvinput.vbox.response.RecordResponse.RecordId) obj);
            }


            {
                this$0 = RecordingSession.this;
                programToRecord = program;
                super();
            }
        });
    }
*/

/*
    public void onStopRecordingChannel(final Channel channelToRecord) {
        Log.d(TAG, "onStopRecording");
        VBoxApp.getDeviceManager().stopRecording(mVBoxChannelId, new com.vboxcomm.android.tvinput.vbox.response.Callback.Get() {

            final RecordingSession this$0;
            final Channel val$channelToRecord;

            public void onResult(Status status, com.vboxcomm.android.tvinput.vbox.response.RecordResponse.RecordId recordid) {
                if (status == null) {
                    createRecordedChannel(channelToRecord);
                    return;
                } else {
                    notifyError(0);
                    Log.d(RecordingSession.TAG, (new StringBuilder()).append("onStopRecordingChannel, recordId: ").append(mRecordId).append(", ").append(status.getDescription()).toString());
                    return;
                }
            }

            public volatile void onResult(Status status, Object obj) {
                onResult(status, (com.vboxcomm.android.tvinput.vbox.response.RecordResponse.RecordId) obj);
            }


            {
                this$0 = RecordingSession.this;
                channelToRecord = channel;
                super();
            }
        });
    }
*/

    public void onTune(Uri uri) {
        super.onTune(uri);
        Log.d(TAG, (new StringBuilder()).append("Tune recording session to: ").append(uri).toString());
        mChannelUri = uri;
        notifyTuned(uri);
    }

    @Override
    public void onStopRecording(Program programToRecord) {

    }

    @Override
    public void onStopRecordingChannel(Channel channelToRecord) {

    }
}

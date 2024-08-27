interface MusicSource {
    void play();
}
class LocalFile {
    public void playLocalFile() {
        System.out.println("Playing music from a local file.");
    }
}
class OnlineStreaming {
    public void streamMusic() {
        System.out.println("Streaming music from an online service.");
    }
}
class RadioStation {
    public void tuneIn() {
        System.out.println("Tuning in to a radio station.");
    }
}
class LocalFileAdapter implements MusicSource {
    private LocalFile localFile;

    public LocalFileAdapter(LocalFile localFile) {
        this.localFile = localFile;
    }
    public void play() {
        localFile.playLocalFile();
    }
}
class OnlineStreamingAdapter implements MusicSource {
    private OnlineStreaming onlineStreaming;

    public OnlineStreamingAdapter(OnlineStreaming onlineStreaming) {
        this.onlineStreaming = onlineStreaming;
    }
    public void play() {
        onlineStreaming.streamMusic();
    }
}
class RadioStationAdapter implements MusicSource {
    private RadioStation radioStation;

    public RadioStationAdapter(RadioStation radioStation) {
        this.radioStation = radioStation;
    }
    public void play() {
        radioStation.tuneIn();
    }
}
interface MusicPlayer {
    void play();
}
class BasicMusicPlayer implements MusicPlayer {
    private MusicSource musicSource;

    public BasicMusicPlayer(MusicSource musicSource) {
        this.musicSource = musicSource;
    }
    public void play() {
        musicSource.play();
    }
}
abstract class MusicPlayerDecorator implements MusicPlayer {
    protected MusicPlayer decoratedMusicPlayer;

    public MusicPlayerDecorator(MusicPlayer decoratedMusicPlayer) {
        this.decoratedMusicPlayer = decoratedMusicPlayer;
    }
    public void play() {
        decoratedMusicPlayer.play();
    }
}
class EqualizerDecorator extends MusicPlayerDecorator {

    public EqualizerDecorator(MusicPlayer decoratedMusicPlayer) {
        super(decoratedMusicPlayer);
    }
    public void play() {
        super.play();
        setEqualizer();
    }

    private void setEqualizer() {
        System.out.println("Applying equalizer settings.");
    }
}
class VolumeControlDecorator extends MusicPlayerDecorator {

    public VolumeControlDecorator(MusicPlayer decoratedMusicPlayer) {
        super(decoratedMusicPlayer);
    }
    public void play() {
        super.play();
        adjustVolume();
    }

    private void adjustVolume() {
        System.out.println("Adjusting volume.");
    }
}
class Main {
    public static void main(String[] args) {
        MusicSource localFile = new LocalFileAdapter(new LocalFile());
        MusicSource onlineStreaming = new OnlineStreamingAdapter(new OnlineStreaming());
        MusicSource radioStation = new RadioStationAdapter(new RadioStation());
        MusicPlayer localMusicPlayer = new BasicMusicPlayer(localFile);
        MusicPlayer onlineMusicPlayer = new BasicMusicPlayer(onlineStreaming);
        MusicPlayer radioMusicPlayer = new BasicMusicPlayer(radioStation);
        MusicPlayer decoratedLocalMusicPlayer = new VolumeControlDecorator(new EqualizerDecorator(localMusicPlayer));
        System.out.println("Playing local file with additional features:");
        decoratedLocalMusicPlayer.play();
        MusicPlayer decoratedOnlineMusicPlayer = new EqualizerDecorator(onlineMusicPlayer);
        System.out.println("\nPlaying online streaming with equalizer:");
        decoratedOnlineMusicPlayer.play();
        MusicPlayer decoratedRadioMusicPlayer = new VolumeControlDecorator(radioMusicPlayer);
        System.out.println("\nPlaying radio station with volume control:");
        decoratedRadioMusicPlayer.play();
    }
}


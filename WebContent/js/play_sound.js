//湖北长虹盒子，屏蔽音乐
function IsHuBeiChangeHongBox() {
	var BoxPlatform = navigator.platform;
	var BoxBrowser = navigator.appName;
	return ((BoxPlatform + ":" + BoxBrowser) == "BCM7213:EIS iPanel");
}

//循环播放
//1主菜单、选择存档、创建角色； 0游戏中普通(3个)； 2普通战斗音乐； 3大乱斗，boss战音乐； 4探险(2个)； 5000+精灵叫； 101新精灵
var flag = -1;
//长音乐，每个只允许一个线程跑，有的情况下，不启用线程
var isRunning1 = false;
var isRunning0 = false;
var PlaySoundType = parseInt("0");
function PlayMusic(flagNO) {
	if (this.IsHuBeiChangeHongBox())
		return;
	if (flag == flagNO)
		return;
	flag = flagNO;
	if (flag > 10000) {
		startMusic_Talk(flagNO - 10000);
	} else if (flag > 1000) {
		startMusic_CG(flagNO - 1000);
	} else {
		switch (flag) {
		case 0:
			startMusic0();
			break;
		case 1:
			startMusic1();
			break;
		}
	}
}
function startMusic1() {
	if (flag == 1) {
		playSound(getRootPath("/include/Music/bg/bgm_1.mp3"), PlaySoundType);
		if (isRunning1 == false) {
			isRunning1 = true;
			setTimeout("isRunning1 = false;startMusic1()", 187000);
		}
	}
}
function startMusic0() {
	if (flag == 0) {
		playSound(getRootPath("/include/Music/bg/bgm_2.mp3"), PlaySoundType);
		if (isRunning0 == false) {
			isRunning0 = true;
			setTimeout("isRunning0 = false;startMusic0()", 100000);
		}
	}
}

function startMusic_Talk(talkID) {
	if (talkID == flag - 10000) {
		playSound(getRootPath("/include/Music/talk/dialog_" + talkID + ".mp3"),
				PlaySoundType);
	}
}
function startMusic_CG(tp) {
	if (tp == flag - 1000) {
		playSound(getRootPath("/include/Music/cg/start_" + tp + ".mp3"),
				PlaySoundType);
	}
}

//创建播放实例
//播放实例
var mp2;
var nativePlayerInstanceID2 = -1;
//初始播放对象
try {
    // 建立背景音乐播放器实例
    mp2 = new MediaPlayer();

    // 获得STB本地播放器实例的instanceID。
    nativePlayerInstanceID2 = mp2.getNativePlayerInstanceID();

    // Media Player的播放模式。
    // 0: 单媒体的播放模式 
    mp2.setSingleOrPlaylistMode(0);

    // MediaPlayer对象对应的视频窗口的显示模式.
    // 255: 视频显示窗口将被关闭。
    // 它将在保持媒体流连接的前提下，隐藏视频窗口。如果流媒体播放没有被暂停，将继续播放音频。
    mp2.setVideoDisplayMode(255);

    // 表示该播放器实例在生命周期内都是否允许任何TrickMode操作（包括快进/快退/暂停）
    // 1: 不允许TrickMode操作
    mp2.setAllowTrickmodeFlag(1);
} catch (e) { mp2 = null; }

// -------------------所有对外方法start-------------------
function playSound(url, playType) {
    try {
        mp2.stop();

        // 媒体对象的mediaURL, 用于播放mp3的地址为http://xxxxx/test.mp3
        var effectmediaStr = playType == 0 ? toJson(url) : url;

        // 设置单个播放的媒体。
        mp2.setSingleMedia(effectmediaStr);

        // 开始播放
        mp2.playFromStart();
    } catch (e) { }
}

//停止播放所有音乐。离开游戏时务必调用
function musicStop() {
    try {
        mp2.stop();
        mp2.releaseMediaPlayer(nativePlayerInstanceID2);
    } catch (e) { }
}
function musicWait() {
    try {
        mp2.stop();
    } catch (e) { }
}
function quietVolume() {
    try {
        var val = mp2.getVolume();
        mp2.setVolume(val == 0 ? 75 : 0);
    } catch (e) { }
}
function upVolume() {
    try {
        var val = mp2.getVolume();
        mp2.setVolume(val + 5);
    } catch (e) { }
}
function downVolume() {
    try {
        var val = mp2.getVolume();
        mp2.setVolume(val - 5);
    } catch (e) { }
}
// -------------------所有对外方法end-------------------
// -------------------内部方法start-------------------
//iptv方式
function toJson(url) {
    return '[{mediaUrl:"' + url
	+ '",mediaCode: "code1",'
	+ 'mediaType:4,'
	+ 'audioType:1,'
	+ 'videoType:1,'
	+ 'streamType:1,'
	+ 'drmType:1,'
	+ 'fingerPrint:0,'
	+ 'copyProtection:1,'
	+ 'allowTrickmode:1,'
	+ 'startTime:0,'
	+ 'endTime:5000,'
	+ 'entryID:"entry1"}]';
}
// -------------------内部方法end-------------------







//播放动画(滚动gif图)
var AnimateObj = new function () {
    this.AnimateStatus = -1,
    this.divObj = null,
    this.pic, this.picLength, this.perPX, this.isXunHuan,
    this.playGif = function (_divID, _pic, _picLength, _perPX, _isXunHuan) {
        this.AnimateStatus = -1;
        this.divObj = $(_divID);
        this.pic = _pic;
        this.picLength = _picLength;
        this.perPX = _perPX;
        this.isXunHuan = _isXunHuan;

        this.divObj.style.backgroundImage = "url(" + this.pic + ")";
        this.divObj.style.backgroundPositionX = "0px";
        setTimeout("AnimateObj.AnimateStatus=0;AnimateObj.playGif_zte(0)", 150);
    },
    this.playGif_zte = function (picCurrent) {
        //背景动画 -- add
        if (bgStep < 3) {
            //$("divEft1").style.backgroundPositionX = "-" + 640 * bgStep + "px";
            if (bgStep == 2) {
                $("divEft1").style.backgroundPositionX = "-" + 640 * bgStep2 + "px";
                if (bgStep2 == 2)
                    bgStep2 = 0;
                else
                    bgStep2++;
            }
            if (bgStep == 2)
                bgStep = 0;
            else
                bgStep++;
        }

        if (this.AnimateStatus == -1) return;
        if (picCurrent < this.picLength) {
            this.divObj.style.backgroundPositionX = "-" + this.perPX * picCurrent + "px";
            setTimeout("AnimateObj.playGif_zte(" + (picCurrent + 1) + ")", 150);
        }
        else {
            if (this.isXunHuan == 0){
                return; //this.divObj.style.display = "none";
            }else {
                this.divObj.style.backgroundPositionX = "0px";
                setTimeout("AnimateObj.playGif_zte(0)", 150);
            }
        }
    }
}
var bgStep = 0;
var bgStep2 = 0;




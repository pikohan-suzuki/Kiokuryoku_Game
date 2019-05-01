package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.AsyncTask
import android.text.PrecomputedText
import android.widget.ImageView
import java.lang.Thread.sleep

class Sound(private val context: Context, private val testTextView: List<ImageView>) {
    private lateinit var soundPool: SoundPool
    private var dogSound = 0
    private var catSound = 0
    private var birdSound = 0
    private var sheepSound = 0

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .build()
        soundPool = SoundPool.Builder()
            .setAudioAttributes(audioAttributes)
            .setMaxStreams(4)
            .build()
    }

    fun play(id: Int) {
        when (id) {
            0 -> soundPool.play(dogSound, 1.0f, 1.0f, 1, 0, 1.0f)
            1 -> soundPool.play(catSound, 1.0f, 1.0f, 1, 0, 1.0f)
            2 -> soundPool.play(birdSound, 1.0f, 1.0f, 1, 0, 1.0f)
            3 -> soundPool.play(sheepSound, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }
    fun playLast(id:Int){
        val playOneSound =
            PlayOneSound(soundPool, arrayListOf(dogSound, catSound, birdSound, sheepSound))
        playOneSound.execute(id)
    }

    fun question(questionMutableList: MutableList<Int>) {
        val playQuestionSound =
            PlayQuestionSound(soundPool, arrayListOf(dogSound, catSound, birdSound, sheepSound), testTextView)
        playQuestionSound.execute(*questionMutableList.toTypedArray())
    }

    fun unload() {
        soundPool.apply {
            unload(dogSound)
            unload(catSound)
            unload(birdSound)
            unload(sheepSound)
        }
    }

    fun load() {
        dogSound = soundPool.load(context, R.raw.dog, 1)
        catSound = soundPool.load(context, R.raw.cat, 1)
        birdSound = soundPool.load(context, R.raw.bird, 1)
        sheepSound = soundPool.load(context, R.raw.sheep, 1)
    }
    class PlayOneSound(
        private val soundPool: SoundPool,
        private val soundList: ArrayList<Int>
    ) : AsyncTask<Int, Int, Int>() {
        override fun doInBackground(vararg id: Int?): Int {
                when (id[0]) {
                    0 -> soundPool.play(soundList[0], 1.0f, 1.0f, 1, 0, 1.0f)
                    1 -> soundPool.play(soundList[1], 1.0f, 1.0f, 1, 0, 1.0f)
                    2 -> soundPool.play(soundList[2], 1.0f, 1.0f, 1, 0, 1.0f)
                    3 -> soundPool.play(soundList[3], 1.0f, 1.0f, 1, 0, 1.0f)
                }
                sleep(1500)
            return 0
        }
    }
    class PlayQuestionSound(
        private val soundPool: SoundPool,
        private val soundList: ArrayList<Int>,
        private val textViewList: List<ImageView>
    ) : AsyncTask<Int, Int, Int>() {
        override fun doInBackground(vararg id: Int?): Int {
            for (i in 0 until id.size) {
                when (id[i]) {
                    0 -> soundPool.play(soundList[0], 1.0f, 1.0f, 1, 0, 1.0f)
                    1 -> soundPool.play(soundList[1], 1.0f, 1.0f, 1, 0, 1.0f)
                    2 -> soundPool.play(soundList[2], 1.0f, 1.0f, 1, 0, 1.0f)
                    3 -> soundPool.play(soundList[3], 1.0f, 1.0f, 1, 0, 1.0f)
                }
                sleep(1500)
            }
            return 0
        }

        override fun onPostExecute(result: Int?) {
            super.onPostExecute(result)
            textViewList.forEach { it.isClickable = true }
        }
    }
}
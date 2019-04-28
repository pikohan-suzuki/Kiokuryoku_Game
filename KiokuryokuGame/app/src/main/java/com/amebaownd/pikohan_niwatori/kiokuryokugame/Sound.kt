package com.amebaownd.pikohan_niwatori.kiokuryokugame

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import java.lang.Thread.sleep

class Sound(private val context: Context) {
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

    fun play(soundName: String):Boolean {
        when (soundName) {
            "dog" -> {

                soundPool.play(dogSound, 1.0f, 1.0f, 1, 0, 1.0f)

            }
            "cat" -> {
                soundPool.play(catSound, 1.0f, 1.0f, 1, 0, 1.0f)

            }
            "bird" -> {
                soundPool.play(birdSound, 1.0f, 1.0f, 1, 0, 1.0f)

            }
            "sheep" -> {
                soundPool.play(sheepSound, 1.0f, 1.0f, 1, 0, 1.0f)

            }
        }
        return true
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

}
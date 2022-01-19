package com.example.a7minuteworkout.ui

import android.app.Application
import android.media.MediaPlayer
import android.net.Uri
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.a7minuteworkout.R
import com.example.a7minuteworkout.model.Exercise
import com.example.a7minuteworkout.util.Constance
import java.lang.Exception
import java.util.*

class ExerciseViewModel(private val application: Application) : ViewModel(),
    TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null

    private var _restTime = MutableLiveData<Long>()
    private val restTime: LiveData<Long> get() = _restTime

    val restTimeString: LiveData<String> = Transformations.map(restTime) {
        it.toString()
    }
    val restTimeInt = Transformations.map(restTime) {
        it.toInt()
    }

    private var _showEx = MutableLiveData<Boolean>()
    val showEx: LiveData<Boolean> get() = _showEx

    private var _showRest = MutableLiveData<Boolean>()
    val showRest: LiveData<Boolean> get() = _showRest

    private var _showImage = MutableLiveData<Boolean>()
    val showImage: LiveData<Boolean> get() = _showImage

    private var _navigateToFinished = MutableLiveData<Boolean>()
    val navigateToFinished: LiveData<Boolean> get() = _navigateToFinished
    fun doneNavigateToFinished() {
        _navigateToFinished.value = false
    }

    private var restTimer: CountDownTimer? = null
    private var restTimerDuration: Long = 10000L

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseTimerDuration: Long = 30000L
    private var _exTime = MutableLiveData<Long>()
    private val exTime: LiveData<Long> get() = _exTime

    val exTimeString = Transformations.map(exTime) { it.toString() }
    val exTimeInt = Transformations.map(exTime) { it.toInt() }

    private var _exerciseList = MutableLiveData<List<Exercise>>(Constance.defaultExerciseList())
    val exerciseList:LiveData<List<Exercise>> get() = _exerciseList

    private var currentExercisePosition = 0

    private val _exerciseName = MutableLiveData<String>()
    val exerciseName: LiveData<String> get() = _exerciseName
    private val _nextExName = MutableLiveData<String>()
    val nextExName: LiveData<String> get() = _nextExName

    private var _imageSrc = MutableLiveData<Int>()
    val imageSrc: LiveData<Int> get() = _imageSrc

    init {
        setRest()
        _showEx.value = false
        _showRest.value = true
        _showImage.value = false
        _nextExName.value = "UPCOMING EXERCISE:\n${exerciseList.value!![currentExercisePosition].name}"
        tts = TextToSpeech(application, this)
    }

    fun setRest() {
        if (restTimer != null) {
            restTimer?.cancel()
        }
        startRestTimer()
        _exerciseName.value = "Get Ready For"

        try {
            val soundURI =
                Uri.parse("android.resource://com.example.a7minuteworkout/" + R.raw.press_start)
            player = MediaPlayer.create(application, soundURI)
            player?.isLooping = false
            player?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun startRestTimer() {
        restTimer = object : CountDownTimer(restTimerDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _restTime.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                _restTime.value = 0L
                _showRest.value = false
                _showEx.value = true
                _showImage.value = true

                setExercise()

            }
        }.start()

    }

    fun setExercise() {
        if (exerciseTimer != null) {
            exerciseTimer?.cancel()
        }
        _imageSrc.value =  _exerciseList.value!![currentExercisePosition].image
        _exerciseName.value =  _exerciseList.value!![currentExercisePosition].name
        _exerciseList.value!![currentExercisePosition].isSelected = true
        tts!!.speak(_exerciseName.value, TextToSpeech.QUEUE_FLUSH, null, "")
        startExerciseTimer()
    }

    private fun startExerciseTimer() {
        exerciseTimer = object : CountDownTimer(exerciseTimerDuration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _exTime.value = millisUntilFinished / 1000
            }
            override fun onFinish() {

                _exerciseList.value!![currentExercisePosition].isSelected = false
                _exerciseList.value!![currentExercisePosition].isComplete = true

                if (currentExercisePosition <  _exerciseList.value!!.size - 1) {
                    _exTime.value = 0L
                    _showRest.value = true
                    _showEx.value = false
                    _showImage.value = false
                    currentExercisePosition++
                    _nextExName.value = "UPCOMING EXERCISE:\n${ _exerciseList.value!![currentExercisePosition].name}"
                    setRest()
                } else {
                    _navigateToFinished.value = true
                }
            }
        }.start()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts?.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }

        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    override fun onCleared() {
        super.onCleared()

        currentExercisePosition = 0
        _nextExName.value = null

        if (restTimer != null) {
            restTimer!!.cancel()
            _restTime.value = 0L
            restTimer = null
            _showRest.value = true
        }

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            _exTime.value = 0L
            exerciseTimer = null
            _showEx.value = false
        }

        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }

        if (player != null) {
            player!!.stop()
        }
    }

}
package com.android.uitestingwithexpresso.ui.movie

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.android.uitestingwithexpresso.R
import com.android.uitestingwithexpresso.ui.movie.ImageViewHasDrawableMatcher.hasDrawable

import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
    @get:Rule
    val intentsTEstRule = IntentsTestRule(MainActivity::class.java)
//    @Test
//    fun test_ValidateGalleryIntent(){
//        val expectedIntent:Matcher<Intent> = allOf(
//            hasAction(Intent.ACTION_PICK),
//            hasData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        )
//        val activityResult = createGalleryPickActivityResultStub()
//        intending(expectedIntent).respondWith(activityResult)
//
//        onView(withId(R.id.button_open_gallery)).perform(click())
//        intended(expectedIntent)
//    }
//
//    private fun createGalleryPickActivityResultStub(): Instrumentation.ActivityResult {
//        val resource: Resources = InstrumentationRegistry.getInstrumentation().context.resources
//        val imageUri = Uri.parse(
//            ContentResolver.SCHEME_ANDROID_RESOURCE+"://"+
//                    resource.getResourcePackageName(R.drawable.ic_launcher_background)+"/"+
//                    resource.getResourceTypeName(R.drawable.ic_launcher_background)+"/"+
//                    resource.getResourceEntryName(R.drawable.ic_launcher_background)
//        )
//        val resultIntent = Intent()
//        resultIntent.data = imageUri
//        return Instrumentation.ActivityResult(RESULT_OK, resultIntent)
//    }

    @Test
    fun test_CameraIntent_isBitmapSetToImageView(){
        val activityResult = createImageCaptureActivityResultStub()
        val expectedIntent:Matcher<Intent> = hasAction(MediaStore.ACTION_IMAGE_CAPTURE)
        intending(expectedIntent)

        //VERIFY
        onView(withId(R.id.image)).check(matches(not(hasDrawable())))
        onView(withId(R.id.button_launch_camera)).perform(click())
        intending(expectedIntent)
        onView(withId(R.id.image)).check(matches(hasDrawable()))
    }
    private fun createImageCaptureActivityResultStub():Instrumentation.ActivityResult{
        val bundle =Bundle()
        bundle.putParcelable(KEY_IMAGE_DATA,BitmapFactory.decodeResource(
            /** this how to get resources and context on the testing case */
            intentsTEstRule.activity.resources, R.drawable.ic_launcher_background))
        val resultData = Intent()
        resultData.putExtras(bundle)
        return Instrumentation.ActivityResult(Activity.RESULT_OK,resultData)
    }
}
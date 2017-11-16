package com.kesteli.filip.lauzhack.introduction;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.kesteli.filip.lauzhack.MainActivity;
import com.kesteli.filip.lauzhack.R;
import com.kesteli.filip.lauzhack.login.SignUpActivity;

public class IntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupSlides();
    }

    private void setupSlides() {

        setFadeAnimation();
//        setSlideOverAnimation();
//        setFlowAnimation();
//        setDepthAnimation();
//        setZoomAnimation();

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance("Summy", "The first summary making app", R.drawable.logo_meal_food, getResources().getColor(R.color.red_300)));
        addSlide(AppIntroFragment.newInstance("Become a Better Writer", "Put your txt file in the app and see the summary of it to know how to get to the point when you're writing", R.drawable.logo_meal_food, getResources().getColor(R.color.red_300)));
        addSlide(AppIntroFragment.newInstance("Become a Better Scientist", "Take a picture of an article and get a summaray from it", R.drawable.logo_meal_food, getResources().getColor(R.color.red_300)));
        addSlide(AppIntroFragment.newInstance("Become a Better Speaker", "Speak with Summy chatbot, improve your speaking skills and make the point by summarizing your speak", R.drawable.logo_meal_food, getResources().getColor(R.color.red_300)));

        // OPTIONAL METHODS
        // Override bar/separator color.
        /*setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));*/

        // Hide Skip/Done button.
//        showSkipButton(true);
        setProgressButtonEnabled(true);


        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //TODO Moram sloziti da ako je user ulogiran, da se prebaci direktno na MainActivity
    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}



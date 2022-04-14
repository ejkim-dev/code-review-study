package com.example.baseandroidapp.util.navigator

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.baseandroidapp.sample.exoplayer.ExoplayerActivity
import com.example.baseandroidapp.sample.motionlayout.YoutubeCloneActivity
import com.example.baseandroidapp.sample.navigation.NavigationActivity
import com.example.baseandroidapp.sample.notification.NotificationActivity
import com.example.baseandroidapp.sample.pip.PipActivity
import com.example.baseandroidapp.util.auth.Authenticator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) {

    enum class Destination(val title : String){
        Navigation("navigation"),
        MotionLayout("motion layout"),
        Notification("notification"),
        Exoplayer("exoplayer"),
        Pip("pip"),
    }


    fun showNavigation(context: Context) =
        context.startActivity(NavigationActivity.callingIntent(context))

    fun showMotionLayout(context: Context) =
        context.startActivity(YoutubeCloneActivity.callingIntent(context))

    fun showNotification(context: Context) =
        context.startActivity(NotificationActivity.callingIntent(context))

    fun showExoplayer(context: Context) =
        context.startActivity(ExoplayerActivity.callingIntent(context))

    fun showPip(context: Context) =
        context.startActivity(PipActivity.callingIntent(context))

    fun move(value: String, context: Context) {
       val intent = when (value) {
            Destination.Navigation.title -> showNavigation(context)
            Destination.MotionLayout.title -> showMotionLayout(context)
            Destination.Notification.title -> showNotification(context)
            Destination.Exoplayer.title -> showExoplayer(context)
            Destination.Pip.title -> showPip(context)
            else-> null
        }
        if(intent == null) {
            Toast.makeText(context, "화면을 찾지 못했습니다.", Toast.LENGTH_SHORT).show()
        }

    }


//    private fun showLogin(context: Context) =
//        context.startActivity(LoginActivity.callingIntent(context))
//
//    fun showMain(context: Context) {
//        when (authenticator.userLoggedIn()) {
//            true -> showMovies(context)
//            false -> showLogin(context)
//        }
//    }
//
//    private fun showMovies(context: Context) =
//        context.startActivity(MoviesActivity.callingIntent(context))
//
//    fun showMovieDetails(activity: FragmentActivity, movie: MovieView, navigationExtras: Extras) {
//        val intent = MovieDetailsActivity.callingIntent(activity, movie)
//        val sharedView = navigationExtras.transitionSharedElement as ImageView
//        val activityOptions = ActivityOptionsCompat
//            .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
//        activity.startActivity(intent, activityOptions.toBundle())
//    }


    class Extras(val transitionSharedElement: View)
}
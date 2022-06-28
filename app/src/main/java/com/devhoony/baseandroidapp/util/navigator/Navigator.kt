package com.devhoony.baseandroidapp.util.navigator

import android.content.Context
import android.view.View
import android.widget.Toast
import com.devhoony.baseandroidapp.sample.exoplayer.ExoplayerActivity
import com.devhoony.baseandroidapp.sample.motionlayout.YoutubeCloneActivity
import com.devhoony.baseandroidapp.sample.navigation.NavigationActivity
import com.devhoony.baseandroidapp.sample.pip.PipActivity
import com.devhoony.baseandroidapp.util.auth.Authenticator
import com.devhoony.baseandroidapp.vod.VodActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) {

    enum class Destination(val title: String) {
        Navigation("navigation"),
        MotionLayout("motion layout"),
        Notification("notification"),
        Exoplayer("exoplayer"),
        Pip("pip"),
        Vod("vod"),
    }


    //    Sample code
    fun showNavigation(context: Context) =
        context.startActivity(NavigationActivity.callingIntent(context))

    fun showMotionLayout(context: Context) =
        context.startActivity(YoutubeCloneActivity.callingIntent(context))

    fun showVodActivity(context: Context) =
        context.startActivity(VodActivity.callingIntent(context))

    fun showExoplayer(context: Context) =
        context.startActivity(ExoplayerActivity.callingIntent(context))

    fun showPip(context: Context) =
        context.startActivity(PipActivity.callingIntent(context))

    fun move(value: String, context: Context) {
        val intent = when (value) {
            Destination.Navigation.title -> showNavigation(context)
            Destination.MotionLayout.title -> showMotionLayout(context)
            Destination.Exoplayer.title -> showExoplayer(context)
            Destination.Pip.title -> showPip(context)
            Destination.Vod.title -> showVodActivity(context)
            else -> null
        }
        if (intent == null) {
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
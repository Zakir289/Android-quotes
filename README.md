# Android-quotes

This is a sample Android project on quotes.


# Android-performance

These are few performance tips which i came across the web
### 1. off-load operations onto threads in background

 Operations that can take a long time should run on their own thread and not in the main (UI) thread. 
 
 If an operation takes too long while it runs on the main thread, the Android OS may show an Application not responding (ANR) dialog : from there, the user may choose to wait or to close your application. 



### 2. stay responsive, Don’t allow “Application Not Responding"  to come up
 creating  a background worker thread by extending AsyncTask and implementing doInBackground() method. 
OR we can go for another option where we create a Thread or HandlerThread class of your own. Keep in mind that unless you specify "background" priority for the thread, it will slow down the app since the default thread priority is the same as of the UI thread.


### 3.Few points to rememeber for better performance and user satisfaction

1. **Progress bar:** Showing the progress to the users using progress bar, instead of showing broken screen or static image.

2. **Splash Screen:** If initial setup is taking time, Its better to show the splash screen instead of showing the hanging screen to the user. 

3. **Using  StrictMode:** to detect potentially lengthy operations you do in the UI thread.

4. **check current states:** of battery and network before launching a full update; wait for better states for bulk transfers.

5. Provide users with battery usage options, e.g. update intervals and background behavior.
6. **Recycle Java objects Manually:** In addition to GC (garbage collection) consider recycling Java objects manually, e.g. XmlPullParserFactory and BitmapFactory; Matcher.reset(newString) for regex; StringBuilder.setLength(0).
7. calculation of a floating point requires lots of battery power, you might consider using microdegrees for bulk geo math and caching values when performing DPI tasks with DisplayMetrics.


### 4.Take care of networking issues

* update your app if only Wifi is connected or at least 3G is connected when there is no roaming 
* Prefer stream parsers over tree parsers
* When possible use framework GZIP libs for text data to make the best use of CPU resources.



### 5.Web Service calls  should always be on their own thread
webservice calls to external API should be on their own thread,
because a network slowdown or a problem on their end can trigger an ANR, blocking the execution of your application. You can also taken advantages of threads to pre-calculate graphics that are displayed later on on the main thread.

### 6. Its better to  avoid sending calls when there is no network
 If your application requires a lot of call to external APIs, avoid sending the calls again and again if the wifi and cellular networks are not available. It is a waste of resources to prepare the whole request, send it off and wait for a timeout when it is sure to fail. You can pool the status of the connexion regularly, switch to an offline mode if no network is available, and reactivate it as soon as the network comes back.

 ### 7.Take advantage of caching to reduce the impact of expensive operations.
 Calculations that are long but for which the result won’t change(static stuff) or graphics that will be reused can be kept in memory.
 You can also cache the result of calls to external APIs to a local database so you won’t depend on that resource being available at all times. A call to a local database can be faster, will not use up your users data plan and will work even it the device is offline. On the other hand, you should plan for a way to fresh that data from time to time, for example keeping a time and date stamp and refreshing it when it’s getting old.


### 8.Save the current state of your activities 
To avoid having to recalculate it when the application is opened again. The data loaded by your activities or the result of any long-running operation should be saved when the **onSaveInstanceState event** is raised and restored when the **onRestoreInstanceState event** is raised.Since the state is saved with a serializable Bundle object, the easiest way to manage state is to have a serializable state object containing all the information needed to restore the activity so only this object needs to be saved. The information entered by the user in View controls is already saved automatically by the Android SDK and does not need to be kept in the state. Remember, the activity state may be lost when the user leaves your application or rotates the screen, not only when the user navigates to another activity.





### 9.Avoid complex layout hierarchies

Make sure your layouts are as simple as possible, without unnecessary layout elements. When the view hierarchy gets too deep, the UI engine have trouble traversing all the views and calculating the position of all elements. 


### 10.Avoid overdrawing:
Too much overdraw can kill an app’s performance
this happens every time the GPU paints a background and then some other graphical artifact above it.. Android supports a “Debug GPU Overdraw” mode that will highlight overdrawn areas by using different colors that show home many times redrawing did happen. So you have a clue at which areas of your layout you should modify.

### 11. Few small thingns to be noted down

**1. Avoid temporrary objectcs** If you have a method returning a string, and you know that its result will always be appended to a StringBuffer anyway, change your signature and implementation so that the function does the append directly, instead of creating a short-lived temporary object.

**2.** When extracting strings from a set of input data, try to return a substring of the original data, instead of creating a copy

**3.** An array of ints is a much better than an array of Integer objects, but this also generalizes to the fact that two parallel arrays of ints are also a lot more efficient than an array of (int,int) objects. The same goes for any combination of primitive types.

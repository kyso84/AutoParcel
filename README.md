AutoParcel
==========

This small library allows you to create parcelable class easily.
It avoids to write the (boring) CREATOR and read/write methods to turn a regular class into a parcelable one.


Basic Usage
———————————
1- Pull AutoParcel project then import AutoParcelLib as an Android project.
2- Include AutoParcelLib as a library of your project.
3- Simply extend your class with "com.kysoprod.autoparcel.lib.AutoParcel".

That’s all: your class is now parcelable :)


Advanced Usage
——————————————

You can manually skip a variable of your class by adding 'transient' keyword.


Known issues
—————————————

It doesn’t work with inner classes.


Your contribution
—————————————————

It works fine for my usage but if you find a bug, don’t hesitate to contact me. 
I will try to take while to fix it.

Thank you
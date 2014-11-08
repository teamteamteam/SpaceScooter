SpaceScooter
============

This isn't the best game in the world. This is just a tribute. And it is a SpaceShooter.

Requirements
------------

You will need to have the following things installed:
* Java Development Kit ***OpenJDK version 1.8.0_25 or higher is recommended!***
* make


Building without eclipse
------------------------

* Clone the repository using git clone (or download a zip from here)
* Run 'make run'
* Enjoy!


Building with eclipse
---------------------

There are some minor things you need to set up to your project in eclipse:

* Tell eclipse to use a version of java >=1.8.
* Add the folder res/ to your *build path*!
* You're now set up to develop and test!

Stats
-----
If you're interested in the amount of code, run 'make stat'.

Troubleshooting
---------------

*The game becomes unresponsable at some point and i have to kill the process?*

There are known issues with Java versions <8 concerning translucency.
Try upgrading to Java 8 or higher.

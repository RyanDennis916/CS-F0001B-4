If you get an error like this:
java.lang.SecurityException: class "org.junit.runner.Describable"'s signer information does not match signer information of other classes in the same package

make sure in the .classpath file: the junit-tools.jar and jmockit.jar in the lib folder both listed at the **bottom**, like this:

<!-- in the .classpath file: -->

`<?xml version="1.0" encoding="UTF-8"?> <classpath> <classpathentry kind="src" path="src"/> <classpathentry kind="lib" path="/Library/Java/javafx-sdk-25/lib/javafx-swt.jar"/> ... <classpathentry kind="lib" path="/Applications/TheiaIDE.app/Contents/Resources/app/plugins/redhat.java/extension/server/plugins/org.hamcrest\_3.0.0.jar"/> <classpathentry kind="con" path="org.eclipse.jdt.launching.JRE\_CONTAINER"/> <classpathentry kind="output" path="bin"/> <classpathentry kind="lib" path="/Users/baba/Desktop/CS1B/CS1B-A4/lib/junit-tools.jar"/> <classpathentry kind="lib" path="/Users/baba/Desktop/CS1B/CS1B-A4/lib/jmockit.jar"/> </classpath>`
More information is at:
https://stackoverflow.com/a/6388117/22054075

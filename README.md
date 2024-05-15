# TalonErinJevonDax
We created an implementation of Pacman. Running the PacMan class will start the game.
The user will also have to ensure they are connected to the database, otherwise, the score
tracking will not run. The build path should also contain the mySQL driver to connect to the database
and a standard OpenJDK library (built on OpenJDK 20.0.2).

To connect to the Colorado College highscore database, use the command:

ssh -v -N -J username@acad-jump1.coloradocollege.edu:8422 username@ma-mathcs.coloradocollege.edu -L 1521:ma-mathcs.coloradocollege.edu:3306

To run the tests, add JUnit 5 to the build path.

HOW TO PLAY:
Use the left-click on your mouse/trackpad to control the menus of the game.
Use the arrow keys (↑ ↓ → ←) to control Pacman's movement and send him in the direction of the arrow.
  - If pacman is travelling in a direction and a different direction is pressed, it will store that
    direction and send him in the new direction when he gets to an intersection.
Use the space bar to pause the game.

Goal:
Collect all the pellets on the map and avoid the ghosts to pass the level!
If the level is passed, the map will reset, but the score will continue updating.
If Pacman is hit by a ghost, he will lose one of three lives.
Survive as long as you can to get a highscore!

FUNCTIONALITY ISSUE:
Currently, exit without save is causing looping/glitching problems. 
We're unsure of whether or not this is a music threading issue, but
we believe it has to do with overall optimization as it was previously working.

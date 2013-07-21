<?php # main.php
#require_once ('./config.php');
include_once ('./header.php');
?>

<a name="THREECARD"></a> <h2>Three Card Game Paradox</h2>
<p>Years ago I came across this three card game, it is where there is three cards, with a prize behind one card, and the player choses one card, then the computer removes one card which doesnt have the prize from the other two cards, and offers the player the chance to stay with the card they have chosen or change to the other card, the thing is most people stay and statistically this is not the best option since you are twice as likely to win by choosing to change. I wrote a program to play the game in BASIC, and some years later in C and Perl, and then java, also run a simulation of a number of games to prove the statistical benefit of changing. Also a python version to simulate the paradox and to learn some python.<br/>
<code>Results:<br/>
Sticking wins you 9973 times.<br/>
Changing wins you 20027 times.<br/>
Changing wins more times. 66% of the time.<br/>
</code>
</p>

<ul>
<li><a target="_blank" href="threecardgame/ThreeCardGame.java">ThreeCardGame.java</a></li>
<li><a target="_blank" href="threecardgame/ThreeCardGame.class">ThreeCardGame.class</a></li>
<li><a target="_blank" href="threecardgame/threecardapplet.html">Three Card Game Simulation Applet</a></li>
<li><a target="_blank" href="threecardgame/threecardgameapplet.html">Three Card Game Applet Play</a></li>
<li><a target="_blank" href="MontySim.jar">Three Card Game Simulation Java Swing</a> (jar file)</li>
<li><a target="_blank" href="three.txt">Three Card Game Simulation</a> (Perl Code)  Output (<a target="_blank" href="outputperl.txt">outputperl.txt</a>) </li>
<li><a target="_blank" href="threec.txt">Three Card Game Simulation</a> (C Code) Output (<a target="_blank" href="outputc.txt">outputc.txt</a>) </li>
<li><a target="_blank" href="threef.txt">Three Card Game Simulation</a> (FORTRAN Code) Output (<a target="_blank" href="outputf.txt">outputf.txt</a>) </li>
<li><a target="_blank" href="threepy.txt">Three Card Game Simulation</a> (Python Code) Output (<a target="_blank" href="outputpy.txt">outputpy.txt</a>) </li>
</ul>
<p>
This is actually called the "Monty Hall Paradox", after a US Game Show I seen recently on Mythbusters which promted me to dig it off my old PC hard drive, the played the game a number of times to prove the theory.
</p>

<p>
By playing the game a large number of times, you eventually find out that, switching (or twisting) is better than sticking with your original because your original card you select it out of three cards, however a twisted card is one of the other two cards, which is two out of three cards, hence staying will be 1/3 correct and twisting will be 2/3 correct!
</p>

<p>
<h3>Output:(Perl)</h3>
<code>Game played 99 times<br/>
Results:<br/>
Sticking wins 26 (26.2626262626263 %)times <br/>
Twisting wins 73 (73.7373737373737 %) times<br/>
The End<br/>
</code>
<br/>
<h3>Output:(C)</h3>
<code>Results:<br/>
Sticking wins: 42 times (37.500000 %)<br/>
Twisting wins: 70 times (62.500000 %)<br/>
It is better to twist.<br/>
The End<br>
</code><br>
<h3>Output:(Fortran)</h3>
<code>Results:<br/>
 Results:<br>
 Sticking wins  336 times (  33.5999985 %).<br/>
 Changing wins  664 times (  66.4000015 %).<br/>
 Changing is better<br>
 The End!<br/>
</code>
<br/>
<h3>Output: (Python)</h3>
<code>Results:<br/>
Sticking wins 355 times (35.5% )<br/>
Changing wins 645 times (64.5% )<br/>
Changing is better!<br/>
The End<br/>
</code>
</p>


<?php

include_once ('./footer.php');

?>

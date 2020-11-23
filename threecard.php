<?php # main.php
#require_once ('./config.php');
include_once ('./header.php');
?>

<a name="THREECARD"></a> <h2>Three Card Game Paradox</h2>
<p>Years ago I came across this three card game, it is where there is three cards, with a prize behind one card, and the player choses one card, then the computer removes one card which doesnt have the prize from the other two cards, and offers the player the chance to stay with the card they have chosen or change to the other card, the thing is most people stay and statistically this is not the best option since you are twice as likely to win by choosing to change. I wrote a program to play the game in BASIC (also fortran and pascal even cobol), and some years later in C and Perl, and then java, also run a simulation of a number of games to prove the statistical benefit of changing. Also a python version to simulate the paradox and to learn some python.<br/>
<code>Results:<br/>
Sticking wins you 9973 times.<br/>
Changing wins you 20027 times.<br/>
Changing wins more times. 66% of the time.<br/>
</code>
</p>

<ul>
<li>Three Card Paradox Simulation and Game LUA. <a target="_blank" href="monty.lua">Lua Code</a> and <a target="_blank" href="outputlua.txt">Output</a> (14/05/13).</li>
<li><a target="_blank" href="http://www.conorgilmer.eu/apps/MontyHall.apk">Three Card Paradox Simulation and Game Android App (21/02/13).</li>
<li><a target="_blank" href="threeonline.php">Three Card Game Simulation in PHP</a> (plays 1000 games) </li>
<li><a target="_blank" href="three.cgi">Three Card Game Simulation</a> (plays 1000 games) (<a target="_blank" href="threepy.txt">Python Code</a>) Output (<a target="_blank" href="outputpy.txt">outputpy.txt</a>) </li>
<li><a target="_blank" href="threecardgame/threecardapplet.html">Three Card Game Simulation Applet</a> Specify Number of Simulations.</li>
<li><a target="_blank" href="threecardgame/threecardgameapplet.html">Three Card Game Applet</a> Play a Game (28/12/01).</li>
<li><a target="_blank" href="MontySim.jar">Three Card Game Simulation Java Swing</a> (executable jar file)</li>
<li><a target="_blank" href="threepl.cgi">Three Card Game Simulation</a> (plays 1000 games) (<a target="_blank" href="three.txt">Perl Code</a>)  Output (<a target="_blank" href="outputperl.txt">outputperl.txt</a>) (28/12/01) </li>
<li>Three Card Game Simulation (C <a target="_blank" href="threec.txt">Code</a>) Output (<a target="_blank" href="outputc.txt">outputc.txt</a>) </li>
<li>Three Card Game Simulation (FORTRAN <a target="_blank" href="threef.txt">Code</a>) Output (<a target="_blank" href="outputf.txt">outputf.txt</a>) </li>
<li>Three Card Game Simulation (COBOL <a target="_blank" href="three.cob">Code</a>) Output (<a target="_blank" href="outputcob.txt">text</a> and <a target="_blank" href="output.rec">record</a>) </li>
<li>Three Card Game Simulation (Pascal <a target="_blank" href="threepas.txt">Code</a>) Output (<a target="_blank" href="outputpas.txt">outputpas.txt</a>) </li>
<li><a target="_blank" href="threecardgame/ThreeCardGame.java">ThreeCardGame.java</a> (Java Code) <a target="_blank" href="threecardgame/ThreeCardGame.class">ThreeCardGame.class</a></li>
<li>Three Card Game Simulation (PHP commandline <a target="_blank" href="threephp.txt">Code</a>) Output (<a target="_blank" href="outputphp.txt">outputphp.txt</a>) </li>
</ul>
<p>
This is actually called the "Monty Hall Paradox", after a US Game Show host, according to Mythbusters episode I seen recently (2011), they played the game a number of times to prove the theory. This promted me to dig it off my old PC hard drive, so Voila!
</p>

<p><h3>Conclusion</h3>
By playing the game a large number of times, you eventually find out that, switching (or twisting) is better than sticking with your original because your original card you select it out of three cards, however a twisted card is one of the other two cards, which is two out of three cards, hence staying will be 1/3 correct and twisting will be 2/3 correct!
</p>

<p>
<h3>Output:(LUA using lua50 on command line)</h3>
<code>Game played 1000 times<br/>
Results:<br/>
Sticking wins 329 times (32.9%)<br/>
Twisting Wins 671 times (67.1%) .<br/>
 End of Monty Hall Simulation  
</code>
<h3>Output:(Perl using perl 5.8)</h3>
<code>Game played 99 times<br/>
Results:<br/>
Sticking wins 26 (26.2626262626263 %)times <br/>
Twisting wins 73 (73.7373737373737 %) times<br/>
The End
</code>
<h3>Output:(C compiled using gcc)</h3>
<code>Results:<br/>
Sticking wins: 42 times (37.500000 %)<br/>
Twisting wins: 70 times (62.500000 %)<br/>
It is better to twist.<br/>
The End
</code>
<h3>Output:(Fortran compiled using g77)</h3>
<code>Results:<br/>
 Sticking wins  336 times (  33.5999985 %).<br/>
 Changing wins  664 times (  66.4000015 %).<br/>
 Changing is better<br>
 The End!
</code>
<h3>Output:(Cobol compiled using opencobol/cobc)</h3>
<code>*** Results ***<br/>
Game played 501 times.<br/>
Sticking wins 157 (31.33%) times, Twisting wins 344 (68.66%) times. <br/>
It's Better to Twist <br/>
*** End of Simulation! ***<br/>
</code>
<h3>Output:(Pascal compiled using gpc)</h3>
<code>Results:<br/>
Game played 100 times<br/>
Sticking wins 37 times (37.00%)<br/>
Changing wins 63 times (63.00%)<br/>
Verdict:<br/>
Twisting wins more!<br/>
The End.
</code>
<h3>Output:(PHP compiled using PHP 5.2)</h3>
<code>Results:<br/>
1000 Games<br/>
Switching wins 317 times (31.7 %)<br/>
Changing wins 683 times (68.3 %)<br/>
Verdict:<br/>
Its better to Change<br/>
The End
</code>

<h3>Output: (Python using python 2.5)</h3>
<code>Results:<br/>
Sticking wins 355 times (35.5% )<br/>
Changing wins 645 times (64.5% )<br/>
Changing is better!<br/>
The End
</code>
<h3>Screenshots Android App.</h3
<table>
<tr><td><img alt="Monty Hall Paradox Game" src="monty250.png"/></td><td> <img  alt="Monty Hall Paradox Screen One"  src="game1.png"/> </td><td><img  alt="Monty Hall Paradox"  src="game2.png"/></td></tr>
</table>


</p>


<p><h3>Comments</h3>
I suppose this was a small program to learn a little bit about various languages. Often the most difficult thing was to get a random number generator particularly in the older languages (Cobol and Fortran), most of the programs there is some stats on the distribution of cards selected.<br> The programs were all compiled using freely available compilers/interpreters for Linux(Ubuntu) with minor modifications from their original code which was specific to Turbo C MS Pascal, MF/PC Cobol etc.
</p>



<?php

include_once ('./footer.php');

?>

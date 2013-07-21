<?php
// three.php
// three card game paradox simulation
// by Conor Gilmer

$stick = 0;
$twist = 0;
$games = 1000;
$stickpc = 0.0;
$twistpc = 0.0;
$cards = array(3);


function run ($games) {
	$winning = rand(1,3);
	$chosen = rand(1,3);
	$cards[1] = 'A';
	$cards[2] = 'B';
	$cards[3] = 'C';
	do {
		$remove = rand(1,3); }
	while ($remove == $winning || $remove == $chosen);

	print "Run $games - Winning $cards[$winning] | Chosen $cards[$chosen] | removed $cards[$remove] - ";
	if ($winning == $chosen){
		print " Sticking Wins.\n";
		return 1;}
	else {
		print " Twisting Wins.\n";
		return 0;}

}

//start
print "Three Card Game Paradox\n";

for ($play=1; $play < $games+1; $play++)
{
	$runret = run($games);
	if ($runret == 1)
		$stick = $stick+1;
	else
		$twist = $twist+1;
}

print "Results:\n";
print "$games Games\n";
$stickpc = ($stick/$games)*100.0;
$twistpc = ($twist/$games)*100.0;
print "Switching wins $stick times ($stickpc %)\n";
print "Changing wins $twist times ($twistpc %)\n";
print "Verdict:\n";
if ($stick > $twist)
  print "Its better to Stick\n";
else
  print "Its better to Change\n";
print "The End\n";
?>

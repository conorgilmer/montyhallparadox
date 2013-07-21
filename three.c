#include <stdio.h>
//#include <ctype.h>

/* Three Card Game Paradox
 * by Conor Gilmer
 */

/* globals */
int n = 0; /* number of Runs */
int a = 0;
int b = 0;
int c = 0;
int randcalled = 0;
float af, bf, cf;

/* return the card corresponding to the number */
char card(int x) 
{
	char cards[] = {'A','B','C'};
	return(cards[x-1]);
} 

int isInt(char *string)
{
        int i;
	int stringLength = (int)strlen(string);

        for(i = 0; i < stringLength; i++)
        {
                if(isdigit(string[i]) == 0 || ispunct(string[i]) != 0)
                        break;
        }

        if(i != stringLength)
                return 1;
        else
                return 0;

}

int randum(void) {
int x;
randcalled++;
x = rand()%3 + 1;
if (x == 1)
	a++;
else if (x == 2)
	b++;
else if (x == 3)
	c++;
else
	printf("something wrong");
return x; 
}

/* Three card game iteration */ 
int run () {

	int winning, chosen, remov;
	n++;
	printf("Run %d \t", n);
	winning = randum();
	chosen = randum();
	//winning = rand()%3 + 1;
	//chosen = rand()%3 + 1;
	
	do {
		remov = randum();
		//remov = rand()%3 + 1;
/*	printf("Removing no %d, (%d, %d) \n", remov, winning, chosen);*/
	} while ((chosen == remov) || (winning == remov));

	printf("Winning no %c \t", card(winning));
	printf("Chosen no %c \t", card(chosen));
	printf("Removing no %c \t", card(remov));

	/* sticking so have you won*/
	if (chosen == winning) {
		printf("Sticking Wins\n");
		return 0;
	} else {
		printf("Twisting Wins\n");
		return 1;
	}
}

int main(int argc, char *argv[])
{
	int twist = 0;
	int stick = 0;
	int games = 1000; /* number of runs */
	int m;
	float twistPC = 0.0;
	float stickPC = 0.0;


	if ( argc != 2) {
         printf("Usage:\n %s Integer\n Run default(%d)",argv[0], games);
	} 
	else {
		if (isInt(argv[1])== 0) {
         		printf("run %s times so\n",argv[1]);
		        games = atoi(argv[1]);
		} else {
			         printf("Usage:\n %s Integer\n Run default(%d)",argv[0], games);}

	}




	printf("Three Card Game Paradox\n");
	/* play the game games times */
	for (m = 1; m < games+1; m++)
	{
		if (run() ==0) 
			stick++; 
		else 
			twist++;
	}

	/* get the percentages of wins for switching and twisting */
	twistPC = (((float)twist/games) * 100.0);
	stickPC = (((float)stick/games) * 100.0);

	/* output results */
	printf("Results:\n");
	printf(" Sticking wins: %d times (%.2f %)\n", stick, stickPC);
	printf(" Twisting wins: %d times (%.2f %)\n", twist, twistPC);
	/* verdict */
	if (stick > twist)
		printf(" It is better to stick with your original choice.\n");
	else
		printf(" It is better to twist.\n");
	printf("The End of Simulation.\n\n");

	printf("How Random is rand() \n");
	printf(" Randum called: %d times\n", randcalled);
	af = ((float)a /(float)randcalled)*100.0;
	bf = ((float)b /(float)randcalled)*100.0;
	cf = ((float)c /(float)randcalled)*100.0;
	printf(" A generated: %d times (%.2f%)\n B generated: %d times (%.2f%)\n C generated: %d times (%.2f%)\n", a, af, b, bf, c, cf);
	printf("The End\n");
	return 0;
}

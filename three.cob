      *> Three Card Game Paradox
      *> three.cob
       identification division.
       program-id.              three.
       author.                  conor gilmer.
       date-written.            january 1998.
       date-compiled.           january 2013.
       environment division.
       input-output section.
       file-control.
        select outfile assign to "output.rec"
        organization is line sequential.
       data division.
       file section.
       fd outfile
        label records are standard.
       01 outrec                pic x(40).
       Working-storage section.
       01 counters.
           02 games                  pic 999 value zero.
           02 stick                  pic 999 value zero.
           02 twist                  pic 999 value zero.
           02 numa                   pic 999 value zero.
           02 numb                   pic 999 value zero.
           02 numc                   pic 999 value zero.
       01 chosencards.
           02 Winning                pic 9  value zero.
           02 chosen                 pic 9  value zero.
           02 remove                 pic 9  value zero.
       01 outputvars.
           02 resultstext            pic X(21) value spaces.
           02 gameresult             pic X(15) value spaces.
       01 percentages.
           02 stickpc                pic 9(2)V99.
           02 twistpc                pic 9(2)V99.
           02 apc                    pic 9(2)V99.
           02 bpc                    pic 9(2)V99.
           02 cpc                    pic 9(2)V99.
       01 randvars.
           02 cnt                    pic 9(4) value zeros.
           02 val usage float-short.
           02 cardrand               pic 9 value zero.

       01 outlinetop.        
                05 star pic x(40) value all '*'.
       01 outlinetext.        
                05 marginleft pic x(2) value all '* '.
                05 linetext pic x(36) value all spaces.
                05 marginright pic x(2) value all ' *'.
       01 outlineresults.        
                05 games-out pic x(5) value all 'Games'.
                05 filler pic x(5).
                05 stick-out pic x(5) value all 'Stick'.
                05 filler pic x(5).
                05 twist-out pic x(5) value all 'Twist'.
                05 filler pic x(5).
                05 result-txt pic x(10) value all 'Better to '.
       01 outlinerand.        
                05 calledr pic x(6) value all 'Called'.
                05 filler pic x(4).
                05 pickeda pic x(5) value all '  A  '.
                05 filler pic x(5).
                05 pickedb pic x(5) value all '  B  '.
                05 filler pic x(5).
                05 pickedc pic x(5) value all '  C  '.
                05 filler pic x(5).
       procedure division.
       main-logic-section.
       begin.
           open output outfile.
           write outrec from outlinetop.
           move "Three Card Game Paradox!" to linetext
           write outrec from outlinetext.
           write outrec from outlinetop.
           write outrec from outlineresults
           write outrec from outlinetop.
           display '*** Three Card Game Paradox! ***'.
           display 'Play the game'.
           perform play
                varying games from 0 by 1 until games >500
           if stick > twist then
                move "It's Better to Stick" to resultstext
                move "Stick!" to result-txt
           else
                move "It's Better to Twist" to resultstext
                move "Twist!" to result-txt
           end-if
                
           display "*** Results ***"
           compute stickpc = (stick/games) *100
           compute twistpc = (twist/games) *100
           display "Game played " games " times."
           display "Sticking wins " stick " (" stickpc "%) times, " 
                "Twisting wins " twist " (" twistpc "%) times. " 
           display resultstext
           move games to games-out
           move stick to stick-out
           move twist to twist-out
      *>   move resultstext to result-txt
           write outrec from outlineresults
           write outrec from outlinetop


           display '*** End of Simulation! ***'.
           
           write outrec from outlinetop
           move "How Random is Random Function!" to linetext
           write outrec from outlinetext.
           write outrec from outlinetop
           write outrec from outlinerand.
           write outrec from outlinetop
           perform randum-analysis.
           
           write outrec from outlinetop.
           close outfile.
           display "Output Statistics written to output.rec"
           stop run.
       main-logic-exit.

      *> Play the game
       play.
           perform randum
           move cardrand to winning
           perform randum
           move cardrand to chosen
           perform randum until cardrand not equal to winning 
                             and cardrand not equal to chosen
           move cardrand to remove
           if chosen = winning then
                compute stick = stick + 1
                move " Sticking Wins!" to gameresult
           else
                compute twist = twist + 1
                move " Twisting Wins!" to gameresult
           end-if
        
           display 'play ' games ' Winning = ' Winning ' chosen = '
                chosen ' removed = ' remove " -" gameresult
       exit.
       play-exit.

      *> Random function
       randum.
       compute cardrand = function mod ((100 * function random() ), 3)
       end-compute
       compute cnt = cnt + 1
       evaluate cardrand
        when 0 compute numA = numA + 1
        when 1 compute numB = numB + 1
        when 2 compute numC = numC + 1
       end-evaluate
       exit.
       randum-exit.

      *> How Random is the random function
       randum-analysis.
       display "*** Random Analysis ***"
       display "How Random is random()"
       display "Random called " cnt " times"
       compute apc = (numa/cnt)*100
       compute bpc = (numb/cnt)*100
       compute cpc = (numc/cnt)*100
       display "A " numa " times ("apc"%), B " numb " times ("bpc"%),"
        " C " numc " times ("cpc"%)."
       move numa to pickeda
       move numb to pickedb
       move numc to pickedc
       move cnt  to calledr
       write outrec from outlinerand
       display "*** The End ***"
       exit.
       randum-analysis-exit.



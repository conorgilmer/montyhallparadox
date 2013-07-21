c three.f
c Three Card Game Paradox
c by Conor Gilmer
c
      program three
c variables
      integer stick, twist, games, outcome, randcalled, a, b, c
      real stickpc, twistpc
      integer run
      common randcalled, a, b, c
     
      stick = 0
      twist = 0
      games = 250
      randcalled = 0
      a = 0
      b = 0
      c = 0

      print *,"Three Card Game Paradox!"
      print *,"Play ", games, " Times"
c run the game games times
      do i=1, games
        outcome = run(i)
        if (outcome == 1) then
                stick = stick + 1
                print *, "Sticking Wins"
        else
                twist = twist + 1
                print *, "Twisting Wins"
        end if
      end do
c output the results
      twistpc = (real(twist)/real(games)) * 100
      stickpc = (real(stick)/real(games)) * 100
      print *,"Results:"
      print *,"Sticking wins ", stick, " times (",stickpc, " %)."
      print *,"Changing wins ", twist, " times (",twistpc, " %)."
      if (stick.le.twist) then
        print *,("Changing is better")
      else
        print *,("Sticking is better")
      endif
      print *,"The End!"

      print *,"How Random is the irand() function?"
      print *,"Random function called ", randcalled
      print *,"A ", a, " times(", (real(a)/real(randcalled))*100, "%)"
      print *,"B ", b, " times(", (real(b)/real(randcalled))*100, "%)"
      print *,"C ", c, " times(", (real(c)/real(randcalled))*100, "%)"
      print *,"End of Random function analysis"
      end program three

c function to generate a simple random number between 1 and 3
c modulus means its numbers between 0 and 3 but if zero random again
      integer function simplerandom(x)
      integer x, randcalled, a, b, c
      common randcalled, a, b, c

c     do
        randcalled = randcalled + 1
        simplerandom = mod(irand(),x)
        if (simplerandom.eq.0) then
                a = a + 1;
        else if (simplerandom.eq.1) then
                b = b + 1;
        else if (simplerandom.eq.2) then
                c = c + 1;
        else 
                c = c;
        endif
c        print*,"Randcalled ", randcalled, "  returning ", simplerandom
c        if (simplerandom.ne.0) exit
c    end do
      return
      end function

c This is a function to play a game and return if stick or twisting won
      integer function run(x)
      integer x
      integer winning, chosen, remove
      character cards(3)
      integer simplerandom
      cards(1) = "A"
      cards(2) = "B"
      cards(3) = "C"
      winning = simplerandom(3)    
c      remove = simplerandom(3)    
      chosen = simplerandom(3)   

      do
       remove = simplerandom(3)    
       if ((remove.ne.winning) .and. (remove.ne.chosen)) exit
      end do
      print *,"winning ",cards(winning+1)," chose ",cards(chosen+1)
      print *,"remove ", cards(remove+1)

      if (winning.eq.chosen) then
        run = 1
      else
        run = 0
      endif
      RETURN 
      END

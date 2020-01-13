// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

(MAIN)
@color
M=0 		// color = 0
@KBD
D=M 		// D = M[KBD]
@WHITEN_SCREEN
D;JEQ 	// if D == 0 then goto WHITEN_SCREEN
@BLACKEN_SCREEN
D;JNE   // if D != 0 then goto BLACKEN_SCREEN

(BLACKEN_SCREEN)
@color
M=-1 		// color = -1
@COLOR_SCREEN
0;JMP 	// goto COLOR_SCREEN

(WHITEN_SCREEN)
@color
M=0 		// color = 0
@COLOR_SCREEN
0;JMP 	// goto COLOR_SCREEN

(COLOR_SCREEN)
@256
D=A
@i
M=D 		// i = 256
@SCREEN
D=A
@screenIterator
M=D 		// M[screenIterator] = 0x4000 (or decimal: 16384, memory map location of screen)
@OUTER_LOOP
0;JMP 	// goto OUTER_LOOP

(OUTER_LOOP)
@i
D=M 		// D = i
@MAIN 
D;JEQ		// if D == 0, goto to main
@i
M=M-1		// i = i-1
@32
D=A
@j 			// j = 32
M=D
@INNER_LOOP
0;JMP 	// goto to INNER_LOOP

(INNER_LOOP)
@j
D=M 		// D = j
@OUTER_LOOP
D;JEQ 	// if D == 0 then goto OUTER_LOOP
@color
D=M 		// D = color
@screenIterator
A=M 	  // A = M[screenIterator] (at first iteration A = 16384)
M=D  		// M[A] = -1 (or binary: 1111111111111111, puts a black line on screen)
@screenIterator
M=M+1 	// M[screenIterator] = M[screenIterator] + 1
@j
M=M-1 	// j = j-1
@INNER_LOOP
0;JMP 	// goto INNER_LOOP
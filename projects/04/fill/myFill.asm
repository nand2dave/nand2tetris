// Pseudocode:
//
// while(true){
//   if (memory[SCREEN] != 0) {
//     color=1;
//	 } else {
//	   color=0;
//   }
//   colorScreen();
// }
//
// colorScreen(){
//   screenIterator = SCREEN;
//   for (i=256; i>0; i--) // each line of the screen
//     for(j=32; j>0; j--) { // each 16-bit row
//       memory[screenIterator]= color;
//       screenIterator = screenIterator+1;
//      }	
//    }
// }


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
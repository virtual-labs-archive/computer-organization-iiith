/*
 

*/
.arm			@ For Thumb mode use .thumb directive

.data

arraysize:	.word 10   				  @ Number of elements in the array
array:		.word 1, -2, 3, -4, 5, -6, 8, -7, 9, -10   @ Array Initialization
sqrsum:	.word						   @ Squared sum to be stored here

.text
.global main     @ 'main' function is mandatory.

@ r0 = arraysize; r1 = &array; r3 = sum; r2 = array[i]
@ Other Registers Used: r6, r7

main:
	ldr r0, =arraysize     @ r0 = &arraysize
        ldr r0, [r0]           @ r0 = arraysize. We are immediately reusing r0. 
        ldr r1, =array	       @ r1 = &array
        mov r3, #0	       @ r3 = 0 (sum = 0)
	cmp r0, #0    	       @ if (r0 == 0) goto exit; 
	beq .Lexit	       

@ We are using r0 as a loop index variable

.Lloop: ldr r2, [r1], #4	
	mla r3, r2, r2, r3     	@ r3 = r2*r2+r3 (sum = sum + array[i]*array[i])
        subs r0, r0, #1	
        bne .Lloop
	ldr r6, =sqrsum        @ r6 = &sqrsum
 	str r3, [r6]           @ *r6 = r3	

.Lexit:
@int sys_exit(int status)
	mov r0, #0	@ Return code
	mov r7, #1	@ sys_exit
	svc 0x00000000


/*

int square(int num)
{
   return num*num;
}

main()
{
  ..........loop begin.........
  sqrsum = sqrsum + square(array[i]);
  ..........loop end...........
}
*/

.arm			@ For Thumb mode use .thumb directive

.data

arraysize:	.word 10
array:		.word 1, -2, 3, -4, 5, -6, 8, -7, 9, -10
sqrsum:	.word

inputparameter:	.word 	0	@ Memory for square function parameter
retval:	.word 		0	@ Memory for return value
saveReight: .word	0	@ Save the registers here before we destroy their contents.
saveRnine: .word	0	@ Restore their contents later.
saveRten: .word		0

.text
.global main     @ 'main' function is mandatory.

@ r0 = arraysize; r1 = &array; r3 = sum; r2 = array[i], r4 = &inputparameter
@ r5 = &retval, r6 = retval

main:
	ldr r0, =arraysize     @ r0 = &arraysize
        ldr r0, [r0]           @ r0 = *r0. We are immediately reusing r0.
        ldr r1, =array
        mov r3, #0
	cmp r0, #0
	beq .Lexit
.Lloop: ldr r2, [r1], #4		@ r2 = array[i]
	ldr r4, =inputparameter		@ Pass array[i] as parameter
	str r2, [r4]
	bl square			@ Call Square. Important: We assume square doesn't
					@ change any register values.
	ldr r5, =retval
	ldr r6, [r5]			@ Read the return value
	add r3, r3, r6
        subs r0, r0, #1
        bne .Lloop
	ldr r6, =sqrsum        @ r6 = &sqrsum
 	str r3, [r6]           @ *r6 = r3	

.Lexit:
@int sys_exit(int status)
	mov r0, #0	@ Return code
	mov r7, #1	@ sys_exit
	svc 0x00000000

@ r9 = {&inputparameter, &retval} , r8 = inputparameter 
@ Other Registers Used: r10
@ Convention: r12 is used as a scratch register. Its value will not be preserved.

square:
	ldr r12, =saveReight		@ Save the r8 contents in data segment
	str r8, [r12]
	ldr r12, =saveRnine		@ Save the r9 contents in data segment
	str r9, [r12]
	ldr r12, =saveRten		@ Save the r10 contents in data segment
	str r10, [r12]
	ldr r9, =inputparameter
	ldr r8, [r9]
 	mul r10, r8, r8
	ldr r9, =retval
	str r10, [r9] 
	ldr r12, =saveReight		@ Restore the r8 contents in data segment
	ldr r8, [r12]
	ldr r12, =saveRnine		@ Restore the r9 contents in data segment
	ldr r9, [r12]
	ldr r12, =saveRten		@ Restore the r10 contents in data segment
	ldr r10, [r12]
	mov pc, lr
	

	.cpu arm10tdmi
	.fpu softvfp
	.eabi_attribute 20, 1
	.eabi_attribute 21, 1
	.eabi_attribute 23, 3
	.eabi_attribute 24, 1
	.eabi_attribute 25, 1
	.eabi_attribute 26, 2
	.eabi_attribute 30, 6
	.eabi_attribute 18, 4
	.file	"flags.c"
	.section	.rodata
	.align	2
.LC0:
	.ascii	"%x\000"
	.align	2
.LC1:
	.ascii	"num1 = %#x num2 = %#x sum = %#x\012\000"
	.align	2
.LC2:
	.ascii	"N = %d  Z = %d C = %d V = %d\012\000"
	.text
	.align	2
	.global	main
	.type	main, %function
main:
	@ args = 0, pretend = 0, frame = 16
	@ frame_needed = 1, uses_anonymous_args = 0
	stmfd	sp!, {r4, r5, r6, r7, r8, r9, fp, lr}
	add	fp, sp, #28
	sub	sp, sp, #24
	str	r0, [fp, #-40]
	str	r1, [fp, #-44]
	mov	r6, #0
	mov	r7, #0
	mov	r8, #0
	mov	r9, #0
	ldr	r3, [fp, #-44]
	add	r3, r3, #4
	ldr	r3, [r3, #0]
	sub	r2, fp, #32
	mov	r0, r3
	ldr	r1, .L3
	bl	sscanf
	ldr	r3, [fp, #-44]
	add	r3, r3, #8
	ldr	r3, [r3, #0]
	sub	r2, fp, #36
	mov	r0, r3
	ldr	r1, .L3
	bl	sscanf
	ldr	r3, [fp, #-32]
	mov	r3, r3, asl #28
	mov	r4, r3
	ldr	r3, [fp, #-36]
	mov	r3, r3, asl #28
	mov	r5, r3
#APP
	adds r11, r4, r5
	movmi r6, #1
	moveq r7, #1
	movcs r8, #1
	movvs r9, #1
	mov	r3, r4
	mov	r2, r5
	mov	ip, fp
	ldr	r0, .L3+4
	mov	r1, r3
	mov	r3, ip
	bl	printf
	mov	r2, r6
	mov	ip, r7
	mov	lr, r8
	mov	r3, r9
	str	r3, [sp, #0]
	ldr	r0, .L3+8
	mov	r1, r2
	mov	r2, ip
	mov	r3, lr
	bl	printf
	ldr	r3, .L3+12
	ldr	r3, [r3, #0]
	mov	r0, r3
	bl	fflush
	mov	r0, #0
	bl	exit
.L4:
	.align	2
.L3:
	.word	.LC0
	.word	.LC1
	.word	.LC2
	.word	stdout
	.size	main, .-main
	.ident	"GCC: (CodeSourcery Sourcery G++ Lite 2007q1-21) 4.2.0 20070413 (prerelease)"
	.section	.note.GNU-stack,"",%progbits

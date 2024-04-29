.text
	.align 4
.globl  gcd
gcd:
gcd_bb2:
gcd_bb3:
	cmpl	%EDI, %EAX
	jne	gcd_bb6
gcd_bb4:
	movl	%ESI, %EAX
gcd_bb1:
	ret
gcd_bb6:
	movl	%ESI, %EAX
	jmp	gcd_bb1
.globl  main
main:
main_bb2:
	pushq	%R14
	pushq	%R15
main_bb3:
	call	input
	call	input
	movl	%R14D, %EDI
	movl	%R15D, %ESI
	call	gcd
	movl	%EAX, %EDI
	call	output
main_bb1:
	popq	%R15
	popq	%R14
	ret

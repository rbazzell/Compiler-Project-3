.text
	.align 4
.globl  main
main:
main_bb2:
	pushq	%R15
main_bb3:
	movl	$0, %EAX
	addl	$68, %EAX
	movl	%EAX, %EDI
	movl	$0, %EAX
	addl	$65, %EAX
	movl	%EAX, %R15D
	call	putchar
	movl	%R15D, %EDI
	call	putchar
	movl	$0, %EAX
	addl	$0, %EAX
main_bb1:
	popq	%R15
	ret

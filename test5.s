.data
.comm	a,4,4

.text
	.align 4
.globl  addThem
addThem:
addThem_bb2:
addThem_bb3:
	addl	%EDI, %EAX
addThem_bb1:
	ret
.globl  main
main:
main_bb2:
	pushq	%RBX
	pushq	%RBP
	pushq	%R13
	pushq	%R14
	pushq	%R15
main_bb3:
	cmpl	%EBP, %ESI
	jne	main_bb6
main_bb4:
	movl	%EBX, a(%RIP)
main_bb5:
	movl	%R10D, %R13D
	cmpl	%EDX, %R9D
	jg	main_bb8
main_bb7:
	movl	%R13D, %EDX
	addl	%R9D, %EDX
	movl	%EDX, %R13D
	movl	%R9D, %EDX
	addl	%R8D, %EDX
	movl	%EDX, %R9D
	cmpl	%EAX, %R9D
	jle	main_bb7
main_bb8:
	movl	$0, %EDX
	movl	%R13D, %EAX
	idivl	%ECX, %EAX
	imull	%EDI, %EAX
	movl	%EAX, %EDI
	movl	%EDI, %R13D
	movl	a(%RIP), %EDI
	call	addThem
	addl	%R13D, %EAX
	movl	%EAX, %EDI
	call	putchar
	movl	%R14D, %EDI
	call	putchar
	movl	%R15D, %EAX
main_bb1:
	popq	%R15
	popq	%R14
	popq	%R13
	popq	%RBP
	popq	%RBX
	ret
main_bb6:
	movl	%R11D, a(%RIP)
	jmp	main_bb5

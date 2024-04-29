.text
	.align 4
.globl  main
main:
main_bb2:
main_bb3:
	movl	$0, %EAX
	addl	$0, %EAX
	movl	$0, %EAX
	addl	$1, %EAX
	movl	$0, %EAX
	addl	$0, %EAX
	movl	%EAX, %EDX
	movl	%EDX, %EAX
	cmpl	%EDX, %EAX
	jle	main_bb6
main_bb4:
	movl	$0, %ESI
	addl	$1, %ESI
	movl	%EDX, %EDI
	subl	%ESI, %EDI
	movl	%EDI, %EDX
main_bb5:
	cmpl	%EDX, %EAX
	jne	main_bb15
main_bb7:
	movl	$0, %EDI
	addl	$2, %EDI
	movl	%EDI, %EDX
	movl	$0, %EDI
	addl	$2, %EDI
	cmpl	%EDI, %EDX
	jne	main_bb11
main_bb9:
	movl	$0, %ESI
	addl	$1, %ESI
	movl	%EDX, %EDI
	addl	%ESI, %EDI
	movl	%EDI, %EDX
main_bb10:
	movl	$0, %EDI
	addl	$2, %EDI
	cmpl	%EDI, %EDX
	je	main_bb7
main_bb12:
	movl	$0, %ESI
	addl	$2, %ESI
	movl	%EDX, %EDI
	addl	%ESI, %EDI
	movl	%EDI, %EDX
	movl	$0, %EDI
	addl	$1, %EDI
	cmpl	%EDI, %EDX
	je	main_bb12
main_bb13:
	jmp	main_bb10
main_bb16:
	cmpl	%EDX, %EAX
	jne	main_bb19
main_bb18:
	movl	$0, %EDI
	addl	$1, %EDI
	movl	%EDX, %EAX
	subl	%EDI, %EAX
	cmpl	%EDX, %EAX
	je	main_bb18
main_bb19:
	cmpl	%EDX, %EAX
	je	main_bb16
main_bb15:
	movl	$0, %EAX
	addl	$3, %EAX
main_bb1:
	ret
main_bb11:
	movl	$0, %EDI
	addl	$1, %EDI
	movl	%EDI, %EDX
	movl	$0, %EDI
	addl	$1, %EDI
	cmpl	%EDI, %EDX
	jne	main_bb10
main_bb6:
	movl	$0, %EDI
	addl	$2, %EDI
	movl	%EDI, %EDX
	movl	$0, %EDI
	addl	$2, %EDI
	cmpl	%EDI, %EDX
	jne	main_bb5

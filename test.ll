(FUNCTION  main  []
  (BB 2
    (OPER 3 Func_Entry []  [])
  )
  (BB 3
    (OPER 4 Add_I [(r 3)]  [(i 0)(i 68)])
    (OPER 5 Mov [(r 1)]  [(r 3)])
    (OPER 6 Add_I [(r 4)]  [(i 0)(i 65)])
    (OPER 7 Mov [(r 2)]  [(r 4)])
    (OPER 8 Pass []  [(r 1)] [(PARAM_NUM 0)])
    (OPER 9 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 10 Mov [(r 5)]  [(m RetReg)])
    (OPER 11 Pass []  [(r 2)] [(PARAM_NUM 0)])
    (OPER 12 JSR []  [(s putchar)] [(numParams 1)])
    (OPER 13 Mov [(r 6)]  [(m RetReg)])
    (OPER 14 Add_I [(r 7)]  [(i 0)(i 0)])
    (OPER 15 Mov [(m RetReg)]  [(r 7)])
    (OPER 16 Jmp []  [(bb 1)])
  )
  (BB 1
    (OPER 1 Func_Exit []  [])
    (OPER 2 Return []  [(m RetReg)])
  )
)

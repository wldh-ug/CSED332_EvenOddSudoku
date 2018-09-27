# Homework 3
> **[Jio Gim](mailto:jio.gim@postech.edu)**, Creative IT Engineering, POSTECH  
> **Student ID:** 20160087, **Povis ID:** iknowme

## How I used CNF to solve E/O Sudoku game

<div style="float:right; text-align:center;">
<img src="images/sudoku.png" style="width: 360px;" alt="Sudoku Board" />
<p style="color: #aaa; text-align:center;">Sudoku Board</p>
</div>

#### Value Mapping
In my solver (named "SUDOKUS"), ![x_{(i,j,k)}](images/x_ijk.png) which means whether if ![i](images/i.png)th row ![j](images/j.png)th column has value ![k](images/k.png) is mapped to ![ijk](images/ijk.png). For example, when ![777](images/777.png) is true, the cell of ![7](images/7.png)th row ![7](images/7.png)th column should have a value of ![7](images/7.png).

#### CNF Making
Below items are conjuncted.  
  + **All possibilities for an empty cell**  
    For all empty cells, add condition of its all possibilities. For instance, in the cell of ![7](images/7.png)th row ![7](images/7.png)th column on right figure, the number ![1](images/1.png), ![3](images/3.png), ![5](images/5.png), ![7](images/7.png), ![9](images/9.png) cannot be in there. So in this case, the condition ![(x_{(7,7,2)}\lor x_{(7,7,4)}\lor x_{(7,7,6)} \lor x_{(7,7,8)})\land \lnot x_{(7,7,1)} \\\land \lnot x_{(7,7,3)} \land \lnot x_{(7,7,5)} \land \lnot x_{(7,7,7)} \land \lnot x_{(7,7,9)}](images/all_possibilities_example.png) will be included to the CNF sentence. In this phase, Even/Odd Sudoku rules are also applied.  
  + **Basic Sudoku rules**  
    There're three major rule: ![1](images/1.png) to ![9](images/9.png) in each sub-grid, in each column, and in each row. This can be expressed in CNF as below.
      - Sub-grid level  
        ![Long Condition](images/basic_rules_subgrid_example.png)
      - Row level
        ![Long Condition](images/basic_rules_row_example.png)
      - Column level
        ![Long Condition](images/basic_rules_subgrid_example.png)

## Development Notes
+ **DO NOT USE** Lombok → It does not supports Java 10.

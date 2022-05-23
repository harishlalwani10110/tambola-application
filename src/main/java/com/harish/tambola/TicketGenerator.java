package com.harish.tambola;

import com.harish.tambola.dto.Ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TicketGenerator {
    public  final Integer maxNumbersInRow = 5;
    public  final Integer maxNumbersInColumn = 3;
    public  Integer[][] ticketData = new Integer[3][9];

    public  Ticket getTicket() {
        makeTicket();
        while(!eachColumnInTicketIsFilled(ticketData)) {
            makeTicket();
        }
        sortAllColumnsInTicket(ticketData);
        Ticket ticket = new Ticket();
        ticket.setTicketData(ticketData);
        return ticket;
        //printTicket(ticket);
    }

    private void sortAllColumnsInTicket(Integer[][] ticket) {
        for(int columnNumber=0; columnNumber < ticket[0].length ; columnNumber++) {
            List<Integer> positionsFilledInColumn = getPositionsFilledInColumn(columnNumber, ticket);
            Collections.sort(positionsFilledInColumn);

            // If there are two numbers in column, swapping may be required.
            if(positionsFilledInColumn.size() == 2) {
                if (ticket[positionsFilledInColumn.get(0)][columnNumber] > ticket[positionsFilledInColumn.get(1)][columnNumber]) {
                    // Swapping the positions
                    int temp = ticket[positionsFilledInColumn.get(0)][columnNumber];
                    ticket[positionsFilledInColumn.get(0)][columnNumber] = ticket[positionsFilledInColumn.get(1)][columnNumber];
                    ticket[positionsFilledInColumn.get(1)][columnNumber] = temp;
                }
            }
            // If there are three numbers in column sorting may be required.
            else if (positionsFilledInColumn.size() == 3){
                List<Integer> numbers  = new ArrayList<Integer>();
                numbers.add(ticket[0][columnNumber]);
                numbers.add(ticket[1][columnNumber]);
                numbers.add(ticket[2][columnNumber]);
                Collections.sort(numbers);

                ticket[0][columnNumber] = numbers.get(0);
                ticket[1][columnNumber] = numbers.get(1);
                ticket[2][columnNumber] = numbers.get(2);
            }
        }
    }
    private List<Integer> getPositionsFilledInColumn(int columnNumber, Integer[][] ticket) {
        List<Integer> positionsFilledInColumn = new ArrayList<Integer>();
        for( int i= 0; i<3;i++) {
            if(ticket[i][columnNumber] != 0) {
                positionsFilledInColumn.add(i);
            }
        }
        return positionsFilledInColumn;
    }

    private void printTicket(int[][] ticket) {
        for(int i =0 ; i < ticket.length ; i++) {
            System.out.println();
            for(int j = 0 ; j < ticket[i].length ; j++) {
                if(ticket[i][j] == 0)
                    System.out.print("   ");
                else
                    System.out.print(ticket[i][j] + "  ");
            }
        }
    }

    private boolean eachColumnInTicketIsFilled(Integer[][] ticket) {
        for(int i =0 ; i < ticket[0].length ; i++) {
            if(ticket[0][i] == 0 && ticket[1][i] ==0 && ticket[2][i] == 0 )
                return false;
        }
        return true;
    }

    private  void makeTicket() {
        for(int i = 0; i< ticketData.length; i++) {
            makeRow(maxNumbersInRow, ticketData[i], ticketData);
        }
    }

    private void makeRow(Integer maxNumbersInRow, Integer[] ticket, Integer[][] fullTicket) {
        Random random = new Random();
        Set<Integer> columnsNoToBeFilled = findColumnsToBeFilledForRow();

        //After we have got columns to be filled, we filled those columns with numbers and rest other with zero
        for( int i=0; i<ticket.length; i++) {
            if(columnsNoToBeFilled.contains(i)) {
                Integer nextNumber = findNextNumberBetween(i*10+1, (i+1)*10, fullTicket);
                ticket[i] = nextNumber;
            }
            else
            {
                ticket[i] = 0;
            }

        }
    }

    private Integer findNextNumberBetween(int min, int max,Integer[][] fullTicket) {
        Random random = new Random();
        int nextNumber  = random.nextInt(max - min + 1) + min;
        while (ticketContains(nextNumber, fullTicket)) {
            nextNumber  = random.nextInt(max - min + 1) + min;
        }
        return nextNumber;
    }

    private boolean ticketContains(int nextNumber, Integer[][] fullTicket) {
        for(int i =0 ; i < fullTicket.length ; i++) {
            for(int j = 0 ; j < fullTicket[i].length ; j++) {
                if(fullTicket[i][j] != null && nextNumber == fullTicket[i][j])
                    return true;
            }
        }
        return false;
    }

    private Set<Integer> findColumnsToBeFilledForRow() {
        Random random = new Random();
        Set<Integer> columnsNoToBeFilled = new  HashSet<Integer>();
        for(int i =0;i<5;i++) {
            if(!columnsNoToBeFilled.add(random.nextInt(9))) {
                i -- ;
            }
        }
        return columnsNoToBeFilled;
    }

    private void printTicketRow(int ticket[]) {
        System.out.println();
        for (int i :ticket){
            if(i ==0){
                System.out.print("   ");
            }
            else {
                System.out.print(i + "  ");
            }
        }
    }
}

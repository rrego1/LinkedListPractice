/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlist;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author reggs
 */
public class LinkedList {
    
    static ArrayList<Node> linkedLists;
    static Scanner stdin = new Scanner(System.in);
        
    public static void main(String[] args) {
        linkedLists = new ArrayList<Node>();

        boolean running = true;
        while(running){
            char selection = getSelection();
            switch(selection){
            case '1':
                Node newList = createNew();
                linkedLists.add(newList);
                break;
            case '2':
                modify();
                break;
            case '3':
                removeList();
                break;
            case '4':
                mergeLists();
                break;
            case '5':
                running = false;
                break;
            }
        }
    }
    
    static char getSelection(){
        char c;

        char [] validInput = {'1', '2', '3', '4', '5'};
        System.out.println("What would you like to do?");
        System.out.println();
        System.out.println("1. Create new List");
        System.out.println("2. Modify List");
        System.out.println("3. Delete List");
        System.out.println("4. Merge Lists");
        System.out.println("5. Exit");

        String input = stdin.next();
        if(input.length() != 1){
            System.out.println("Invalid selection: Please enter a valid choice");
            return getSelection();
        }else{
                c = input.charAt(0);
        }
        if(c != 0){
            for(int i = 0; i < validInput.length; i++){
                if(validInput[i] == c){
                    return c;
                }
            }
            System.out.println("Invalid selection: Please enter a valid choice." + "here");
            return getSelection();
        }
        return c;
    }
    
    static Node createNew(){

        System.out.println("Enter the numbers in the list one by one"
                        + " and enter when the list is complete");

        Node head = null;
        Node currentNode = null;
        boolean creatingList = true;
        String input = stdin.nextLine();

        while(creatingList){
            input = stdin.nextLine();
            if(input.isEmpty()){
                break;
            }
            int val = Integer.parseInt(input);
            Node node = new Node(val);
            if(head == null){
                head = node;
                currentNode = node;
            }else{
                currentNode.setNext(node);
                currentNode = node;
            }
        }
        if(head == null){
            System.out.println("List must contain at list node.");
            return createNew();
        }
        System.out.println("This list your created is:");
        printList(head);
        return head;
    }
    
    static void modify(){
        System.out.println("What list do you want to modify?");
        printAllLists();
        int listNumber = stdin.nextInt();
        if(listNumber > 0 && listNumber <= linkedLists.size()){
            System.out.println("What change do you want to make?");
            System.out.println("1. Delete Element");
            System.out.println("2. Add Element");
            System.out.println("3. Reverse List");
            System.out.println("4. Sort Increasing Order");
            System.out.println("5. Sort Decreasing Order");
            int selection = stdin.nextInt();
            int index;
            switch (selection) {
                case 1:
                    System.out.println("Enter the index for the node you want to delete. (0 indexed)");
                    index = stdin.nextInt();
                    remove(linkedLists.get(listNumber - 1), index);
                    printList(linkedLists.get(listNumber - 1));
                    break;                  
                case 2:   
                    System.out.println("Enter the value for the new node.");
                    int val = stdin.nextInt();
                    Node node = new Node(val);
                    System.out.println("Enter the index for the position of the new node. (0 indexed)");
                    index = stdin.nextInt();
                    insert(linkedLists.get(listNumber - 1), node, index);
                    printList(linkedLists.get(listNumber - 1));
                    break;    
                case 3:
                    Node newList = reverse(linkedLists.get(listNumber - 1));
                    printList(newList);
                    linkedLists.remove(listNumber - 1);
                    linkedLists.add(listNumber - 1, newList);
                    break;
                case 4:
                    increasingOrderSort(linkedLists.get(listNumber - 1));
                    printList(linkedLists.get(listNumber - 1));
                    break;
                case 5:
                    decreasingOrderSort(linkedLists.get(listNumber - 1));
                    printList(linkedLists.get(listNumber - 1));
                    break;
                default:
                    System.out.println("Invalid Selection");
                    modify();
                    break;
            }
        }else{
            System.out.println("Invalid List");
            modify();
        }
    }
    
    static void removeList(){
        System.out.println("While list do you want to delete?");
        printAllLists();
        int listNumber = stdin.nextInt();
        linkedLists.remove(listNumber - 1);
        System.out.println("List removed.");
    }
    
    static void insert(Node head, Node newNode, int index){
        Node current = head;
        for(int i = 0; i < index + 1; i++){
            if(i == index - 1){
                newNode.setNext(current.getNext());
                current.setNext(newNode);
                break;
            }else{
                if(current != null){
                    current = current.getNext();
                }
            }
        }
    }
    
    static void remove(Node head, int index){
        Node current = head;
        Node previous = head;
        for(int i = 0; i < index; i++){
            if(current != null){
                if(current.getNext() != null){
                    previous = current;
                }
                current = current.getNext();
            }
        }
        if(current != null){
            previous.setNext(current.getNext());
        }else{
            previous.setNext(null);
        }

    }

    static Node reverse(Node head){
        Node current = head;
        Node newHead = null;
        while(current != null){
            if(newHead == null){
                newHead = new Node(current.getVal());
                current = current.getNext();
                newHead.setNext(null);
            }else{
                Node newNode = new Node(current.getVal());
                newNode.setNext(newHead);
                newHead = newNode;
                current = current.getNext();
            }  
        }
        return newHead;
    }

    static void mergeLists(){
        if(linkedLists.size() < 2){
            System.out.println("Not enough lists to merge");
        }else{
            System.out.println("Which lists do you want to merge?");
            printAllLists();
            int first = stdin.nextInt();
            int second = stdin.nextInt();
            if(first <= linkedLists.size() && first > 0 && second <= linkedLists.size() && second > 0){
                Node firstList = linkedLists.get(first - 1);
                Node secondList = linkedLists.get(second - 1);
                while(firstList.getNext() != null){
                    firstList = firstList.getNext();
                }
                firstList.setNext(secondList);
                linkedLists.remove(secondList);
                printList(linkedLists.get(first - 1));
            }else{
                System.out.println("Invalid list selection");
                mergeLists();
            }
        }
    }

    static void sumLists(Node headOne, Node headTwo){
        headOne = reverse(headOne);
        headTwo = reverse(headTwo);
        Node newHead = null;
        boolean addOne = false;
        while(headOne != null || headTwo != null){
            int val1 = 0;
            int val2= 0;
            if(headOne != null){
                val1 = headOne.getVal();
            }
            if(headTwo != null){
                val2 = headTwo.getVal();
            }
            int val = val1 + val2;
            if(addOne){
                val++;
                addOne = false;
            }
            if(val > 9){
                val = val % 10;
                addOne = true;
            }
            Node newNode = new Node(val);
            if(newHead == null){
                newHead = newNode;
            }else{
                newNode.setNext(newHead);
                newHead = newNode;
            }
        }
        if(addOne){
            Node newNode = new Node(1);
            newNode.setNext(newHead);
            newHead = newNode;
        }
        printList(newHead);
        linkedLists.add(newHead);
    }
    
    static void increasingOrderSort(Node head){
        Node newHead = null;
        Node temp = head;
        while(temp != null){
            if(newHead == null){
                newHead = new Node(temp.getVal());
            }else{
                newHead = insertIncreasingOrder(newHead, temp);
            }
            temp = temp.getNext();
        }
        int index = linkedLists.indexOf(head);
        linkedLists.remove(head);
        linkedLists.add(index, newHead);
    }
    
    static Node insertIncreasingOrder(Node head, Node node){     
        Node prev = null;
        Node current = head;
        Node newNode = new Node(node.getVal());
        while(current != null){
            if(prev == null){
                if(current.getVal() > node.getVal()){
                    newNode.setNext(current);
                    return newNode;
                }else{
                    prev = current;
                    current = current.getNext();
                }
            }else{
                if(current.getVal() < node.getVal()){
                    prev = current;
                    current = current.getNext();
                }else{
                    Node next = current.getNext();
                    current.setNext(newNode);
                    newNode.setNext(next);
                    return head;
                }
            }
        }
        prev.setNext(newNode);
        return head;
    }
    
    static void decreasingOrderSort(Node head){
        Node newHead = null;
        while(head != null){
            if(newHead == null){
                newHead = head;
            }else{
                newHead = insertDecreasingOrder(newHead, head);
            }
        }
        int index = linkedLists.indexOf(head);
        linkedLists.remove(head);
        linkedLists.add(index, newHead);
    }
    
    static Node insertDecreasingOrder(Node head, Node node){
       return null;
    }
    
    static void printList(Node head){
        Node current = head;
        while(current != null){
            System.out.print(current.getVal());
            if(current.getNext() != null){
                System.out.print("->");
                current = current.getNext();
            }else{
                current = current.getNext();
                System.out.println();
            }
        }
    }
    
    static void printAllLists(){
        int count = 1;
        for(Node node : linkedLists){
            System.out.print(count + ". ");
            printList(node);
            count++;
        }
    }
    
}

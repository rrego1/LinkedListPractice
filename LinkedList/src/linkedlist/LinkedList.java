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
                    running = false;
                    break;
            }
        }
    }
    
    static char getSelection(){
        char c;

        char [] validInput = {'1', '2', '3', '4'};
        System.out.println("What would you like to do?");
        System.out.println();
        System.out.println("1. Create new List");
        System.out.println("2. Modify List");
        System.out.println("Delete List");
        System.out.println("3. Exit");

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
        System.out.println("While list do you want to modify?");
        printAllLists();
        int listNumber = stdin.nextInt();
        if(listNumber > 0 && listNumber <= linkedLists.size()){
            System.out.println("What change do you want to make?");
            System.out.println("1. Delete Element");
            System.out.println("2. Add Element");
            System.out.println("3. Reverse List");
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

    static void addLists(){

    }

    static void sumLists(){

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

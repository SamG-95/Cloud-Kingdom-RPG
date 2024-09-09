import java.util.Scanner;
import java.io.*;
public class texter {
    //controls when the game is over, not something stored by savedata
    public static boolean gameOver = false;
    //lines 7-18 are variables stored by save data
    public static int roomActive = 1;
    public static int hasSword = 0;
    public static int sealBroken = 0;
    public static int hasTurtle = 0;
    public static int hasKey = 0;
    public static int doorUnlocked = 0;
    public static int hasBookP = 0;
    public static int hasBookK = 0;
    public static int bookshelfOpened = 0;
    public static int hasBlueOrb = 0;
    public static int hasRedOrb = 0;
    public static int hasTorch = 0;
    //just a scanner
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        //fancy title screen
        System.out.println("$$$$$$$$\\ $$\\   $$\\ $$$$$$$$\\        $$$$$$\\  $$\\       $$$$$$\\  $$\\   $$\\ $$$$$$$\\  \n\\__$$  __|$$ |  $$ |$$  _____|      $$  __$$\\ $$ |     $$  __$$\\ $$ |  $$ |$$  __$$\\\n   $$ |   $$ |  $$ |$$ |            $$ /  \\__|$$ |     $$ /  $$ |$$ |  $$ |$$ |  $$ |\n   $$ |   $$$$$$$$ |$$$$$\\          $$ |      $$ |     $$ |  $$ |$$ |  $$ |$$ |  $$ |\n   $$ |   $$  __$$ |$$  __|         $$ |      $$ |     $$ |  $$ |$$ |  $$ |$$ |  $$ |\n   $$ |   $$ |  $$ |$$ |            $$ |  $$\\ $$ |     $$ |  $$ |$$ |  $$ |$$ |  $$ |\n   $$ |   $$ |  $$ |$$$$$$$$\\       \\$$$$$$  |$$$$$$$$\\ $$$$$$  |\\$$$$$$  |$$$$$$$  |\n   \\__|   \\__|  \\__|\\________|       \\______/ \\________|\\______/  \\______/ \\_______/\n\n\n\n      $$\\   $$\\ $$$$$$\\ $$\\   $$\\  $$$$$$\\  $$$$$$$\\   $$$$$$\\  $$\\      $$\\    \n      $$ | $$  |\\_$$  _|$$$\\  $$ |$$  __$$\\ $$  __$$\\ $$  __$$\\ $$$\\    $$$ |     \n      $$ |$$  /   $$ |  $$$$\\ $$ |$$ /  \\__|$$ |  $$ |$$ /  $$ |$$$$\\  $$$$ |     \n      $$$$$  /    $$ |  $$ $$\\$$ |$$ |$$$$\\ $$ |  $$ |$$ |  $$ |$$\\$$\\$$ $$ |  \n      $$  $$<     $$ |  $$ \\$$$$ |$$ |\\_$$ |$$ |  $$ |$$ |  $$ |$$ \\$$$  $$ |  \n      $$ |\\$$\\    $$ |  $$ |\\$$$ |$$ |  $$ |$$ |  $$ |$$ |  $$ |$$ |\\$  /$$ |       \n      $$ | \\$$\\ $$$$$$\\ $$ | \\$$ |\\$$$$$$  |$$$$$$$  | $$$$$$  |$$ | \\_/ $$ |        \n      \\__|  \\__|\\______|\\__|  \\__| \\______/ \\_______/  \\______/ \\__|     \\__|     \n");
        String loadQuestion = new String();
        //handles saves
        System.out.println("Welcome to the game! Would you like to load a previous save? (Yes/No)");
        loadQuestion = scanner.next();
        if(loadQuestion.equalsIgnoreCase("Yes")){
            String saveType = new String();
            System.out.println("Would you like to load an autosave (type 'a') or a manual save (type 'm')?");
            saveType=scanner.next();
            if(saveType.equalsIgnoreCase("a")){
                loadAuto();
            } else if (saveType.equalsIgnoreCase("m")){
                load();
            } else {
                System.out.println("Failed to load save data. If you meant to use your autosave, restart this program to avoid it being overwritten.");
                pressEnter();
            }
        }
        while(gameOver==false){
            //triggers an autosave before you go into each room.
            autosave();
            //as roomActive changes, the room you're inside in goToRoom changes with it.
            goToRoom(roomActive);
            
        }
    }
    public static void goToRoom(int roomNum){
        Scanner keyboard = new Scanner(System.in);
        String choice = new String();
        String direction = new String();
        if(roomNum==1){
            System.out.println("\nYour journey starts in a forest, with large trees behind you, and structures to the north, west, and east of you.\n");
            //In most rooms, you'll only have the ability to move, open the menu, or observe the area.
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            //You'll see this if statement in every room, and it triggers an observation about your surroundings. Every room has its own observation.
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.println("Many large trees lie to the south of you. \nIt seems like all you can do right now is move north, to the building in front of you, to the east, where you'll encounter a ladder surrounded by clouds, or to the west, where you'll encounter a cavern leading underground.");
                pressEnter();
            }
            //You'll also see this in every room, it handles the direction that you move.
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    //when roomActive is changed, the main method will now trigger goToRoom to go to a different room
                    roomActive=3;
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=28;
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=2;
                    System.out.println("\n************************\n****TRAVELER'S GUILD****\n************************\n");
                }
                if(direction.equalsIgnoreCase("south")){
                System.out.println("Large trees block you from moving in that direction.");
                }
                
            }
            //Player can access the menu at any time by typing "menu", "open the menu", or "open"
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        //Room two is the Traveler's Guild, an important location in the game.
        if(roomNum==2){
            System.out.println("\nNow inside the guild, you find other humans talking to each other and having a good time. \nYou stand there for a minute, and then a small figure comes up to you.\n");
            //The option to talk is added in rooms where it's applicable.
            System.out.println("Would you like to move, open the menu, observe the area, or talk?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("\nIn the midst of the crowd of people, you see a large door. I wonder what it leads to? ");
                //pressEnter() makes it so the user has to press enter in order to progress. That way, they aren't bombarded by a ton of dialogue.
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The exit is to the south!");
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The exit is to the south!");
                }
                if(direction.equalsIgnoreCase("north")){
                    //The seal is something that can only be broken at the very end of the game, and requires you to go through both the cloud kingdom and the lava depths in order for it to be opened. Check map for what those locations are. The sequence that follows if sealBorken==1 is the ending scene of the game.
                    if(sealBroken==1){
                        System.out.print("You walk towards the door at the back of the guild, and notice your orbs start to glow.\nYou're about to grant your wish, but something doesn't feel right. ");
                        pressEnter();
                        System.out.print("You call the King and Princess over, and say, \n'If it wasn't for me, your kingdom would still be here today. Using this wish, I'll restore the Cloud Kingdom to the way it used to be.' ");
                        pressEnter();
                        System.out.print("LADY: 'Oh traveler... are you positive that this is the wish you want to make?' ");
                        pressEnter();
                        System.out.print("OLD MAN: 'It's thanks to you that me and my daughter are standing here today.\nSure, our kindgom might be gone, but we have each other now. Please be sure about your choice.' ");
                        pressEnter();
                        System.out.print("'I'm certain,' you say. ");
                        pressEnter();
                        System.out.print("\nYou cast your wish, and the sky begins to change from gray to blue. The clouds reform in the horizon, and it seems that all is back to normal. ");
                        pressEnter();
                        System.out.println("You proclaim to the guild that as long as you live, you'll make sure to be there for others who are feeling alone.");
                        pressEnter();
                        System.out.println("\n\n               FIN              \n\nMade by Sam Gutterman, YHS Class of 2023.");
                        //ends the while loop in the main method, which ends the game
                        gameOver=true;
                    }else{
                        System.out.println("The exit is to the south!");
                    }
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=1;
                }
                
            }
            if(choice.equalsIgnoreCase("talk")){
                //many if statements here as talking to the gnome at different progression points in the story will result in different conversations
                if(hasBlueOrb==0){
                if(hasSword==0){
                System.out.print("\nGNOME: 'Welcome to the Traveler's Guild! Here, we prepare adventurers for whatever faces them ahead.' ");
                pressEnter();
                System.out.println("GNOME: 'It looks like you don't have a weapon yet, so please, take this sword!'\n\nSWORD OBTAINED!\n\n");
                pressEnter();
                //Sword is now added to your inventory.
                hasSword=1;
                } else {
                    System.out.print("\nGNOME: 'Hello again, traveler! Please be safe on your adventure!' ");
                    pressEnter();
                }
            } else if(hasRedOrb==0) {
                System.out.print("GNOME: 'Hello, traveler. Bring me back the red orb from the depths of the kingdom. Only then can your wish be granted!' ");
                pressEnter();
            } else {
                System.out.print("GNOME: 'So, you've obtained both orbs. Give them to me, if you're so willing.' ");
                pressEnter();
                System.out.println("\n\nSEAL BROKEN!\n\n");
                pressEnter();
                System.out.print("GNOME: 'Move north, and grant your wish!' ");
                pressEnter();
                //seal is now broken, allowing the player to move north and finish the game.
                sealBroken=1;
            }
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        //Entrance to the cloud kingdom
        if(roomNum==3){
            //You're able to access the ladder and climb it as long as you don't have the blue orb.
            if(hasBlueOrb==0){
            System.out.println("\nYou approach a giant ladder coming down from the clouds above.\n");
            System.out.println("Would you like to move, open the menu, observe the area, or climb the ladder?");
            } else {
            //Once you have the blue orb, the ladder is destroyed, which locks off transportation to the cloud kingdom
            System.out.println("\nThe giant ladder from before seems to be gone.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            }
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                //Different observation messages based on if you have the blue orb or not
                if(hasBlueOrb==0){
                System.out.print("It looks like all you can do here is climb the ladder or go back west. ");
                pressEnter();
                } else {
                System.out.print("The ladder you once used to climb to the Cloud Kingdom is now gone. All you can do is go back west. ");
                pressEnter();    
                }
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("Large trees block you from moving in that direction.");
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=1;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("Large trees block you from moving in that direction.");
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("Large trees block you from moving in that direction.");
                }
                
            }
            //Have to make sure there aren't any loopholes to get back up, so i made the climb command only work when someone doesn't have the blue orb.
            if(hasBlueOrb==0){
            if(choice.equalsIgnoreCase("climb")||choice.equalsIgnoreCase("climb the ladder")){
                System.out.println("\n*********************\n****CLOUD KINGDOM****\n*********************\n");
                roomActive=4;
            }
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        //Now inside the Cloud Kingdom
        if(roomNum==4){
            System.out.println("\nWelcome to the clouds! You've arrived in the Cloud Kingdom! \nA path between clouds lies in front of you.\n");
            //You're able to climb down at any time and go back to the ground level.
            System.out.println("Would you like to move, open the menu, observe the area, or climb down the ladder?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("With heavy clouds surrounding you, it looks like there's a clear path north! ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=5;
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The clouds are too thick here.");
                }
                
            }
            //lets the player climb back down
            if(choice.equalsIgnoreCase("climb")||choice.equalsIgnoreCase("climb down")||choice.equalsIgnoreCase("climb down the ladder")){
                roomActive=3;
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        if(roomNum==5){
            System.out.println("\nYou're walking amongst a path of clouds.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("With heavy clouds surrounding you, it looks like you can only move east or west, or go back south. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=9;
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=6;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=4;
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        if(roomNum==6){
            //gives a little hint that there are buildings nearby
            System.out.println("\nYou're walking amongst a path of clouds, and notice buildings to the north and south.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.println("With heavy clouds surrounding you, it appears that your movement is limited. \n You can move to a building reminscent of a dojo in the north, a building reminscent of a house in the south, or go back to where you came from.");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=5;
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The clouds are too thick here.");
                }
                //North will lead you to the Cloud Dojo, and south will lead you to the Cloud Condo. 
                //You need to use the sword from the Traveler's Guild in order to battle in the dojo, and you need to battle in the dojo to give the stolen turtle back to the woman in the condo. You can go to these areas in any order, and hints will be given if you don't meet a specific requirement.
                if(direction.equalsIgnoreCase("north")){
                    roomActive=7;
                    System.out.println("\n************************\n*******CLOUD DOJO*******\n************************\n");
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=8;
                    System.out.println("\n*************************\n*******CLOUD CONDO*******\n*************************\n");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }

        if(roomNum==7){
            System.out.println("\nYou've entered a dojo of some sort, where an angry old man stands before you.\n");
            System.out.println("Would you like to move, open the menu, observe the area, or talk?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.println("Many types of weapons are on the wall, and seem to be much taller than the old man.");
                //If you haven't fought yet, it gives a hint
                if(hasTurtle==0){
                System.out.println("It appears as if the old man is protecting something... What could it be?");
                }
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The only way to escape the dojo is to exit the same way you came in!");
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The only way to escape the dojo is to exit the same way you came in!");
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The only way to escape the dojo is to exit the same way you came in!");
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=6;
                }
                
                
            }
            if(choice.equalsIgnoreCase("talk")){
                String okToBattle = new String();
                //when hasTurtle==0, the old man hasn't been battled with
                if(hasTurtle==0){
                System.out.print("OLD MAN: 'What are you doing here?! You aren't here because of HER, are you?'\nYou start to wonder who HER could be... ");
                pressEnter();
                System.out.println("OLD MAN: 'Nomatter, if you truly want to take my treasure, you're going to need to battle me for it!' \n\nType 'yes' if you'll accept this battle, or type anything else if you're not ready.");
                okToBattle=keyboard.next();
                if(!okToBattle.equalsIgnoreCase("yes")){
                    System.out.println("\nOLD MAN: 'Come back when you're ready, kid. I'll be waiting.'");
                }else{
                    //need sword in order to battle.
                if(hasSword==0){
                System.out.println("OLD MAN: 'Wait... kid... do you not have a weapon?\nI'll only battle serious challengers, so don't come back until you have a real weapon!' ");
                pressEnter();
                } else {
                    System.out.print("\nYou pull out your sword, and clash with the man! \nPress enter to attack:");
                    //For some, pausing the text wouldn't work without two scanner.nextLine()'s.
                    scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("\nOLD MAN's HP: [IIIIII   ]");
                    System.out.print("OLD MAN: 'Wow, you're strong for someone you're age. But don't let that hit fool you!'\nPress enter to attack:");
                    scanner.nextLine();
                    System.out.println("\nOLD MAN's HP: [III     ]");
                    System.out.print("OLD MAN: 'So, you got lucky again. But there's no way you hit me this time!'\nPress enter to attack:");
                    scanner.nextLine();
                    System.out.println("\nOLD MAN's HP: [        ]");
                    System.out.println("\nOLD MAN: 'Well, it looks like you defeated me. Here's your prize, I shouldn't have taken it.'");
                    System.out.println("\n\nTURTLE OBTAINED!\n\n");
                    pressEnter();
                    //Turtle obtained, unlocking the cloud condo's mission and changing dialogue.
                    hasTurtle=1;

                }
                }
                } else {
                    //When you have the turtle, he now says this.
                    System.out.print("\nOLD MAN: 'Please, take it back to her safely. And tell her I'm sorry.' ");
                    pressEnter();
                }
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        if(roomNum==8){
            if(hasKey==0){
                System.out.println("\nYou've entered a condo of some sort, where a saddened lady sits alone at a table.\n");
            }
            System.out.println("Would you like to move, open the menu, observe the area, or talk?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                //Just a little easter egg here; when you first meet her, she's sad without her turtle. But after she gets it back, she's happy again.
                if(hasKey==0){
                    System.out.println("The house has a colorful vibe, contradicting the mood of the lady sitting in the room.\nIt appears as if the lady is missing something... What could it be?");
                } else {
                    System.out.println("The house has a colorful vibe, matching the mood of the lady sitting in the room.");
                }
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The only way to leave the condo is to exit the same way you came in!");
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The only way to leave the condo is to exit the same way you came in!");
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=6;
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The only way to leave the condo is to exit the same way you came in!");
                }
                
            }
            if(choice.equalsIgnoreCase("talk")){
                //hasKey is very similar to hasTurtle in the dojo, it keeps track of not only the key item, but if the quest in the condo was completed.
                if(hasKey==0){
                String turtersQuestion = new String();
                System.out.println("\nLADY: 'Oh, hello, don't mind me. Just lost in my thoughts without my sweet Turters.'\nAsk who Turters is? (Yes or No)");
                turtersQuestion=keyboard.next();
                //need to say yes to progress
                if(turtersQuestion.equalsIgnoreCase("Yes")){
                    System.out.print("\nLADY: 'Well, my pet turtle, Turters, was taken from me by a cruel man.\nAll I want is to see Turters again, but I'm afraid that might never happen.' ");
                    pressEnter();
                //story only progresses if you have the turtle, if you don't you get a hint saying that you have to find the missing turtle.
                if(hasTurtle==0){
                System.out.println("\n(Looks like it's time to find this missing turtle!)");
                } else {
                    System.out.print("LADY: 'My heavens... is that Turters I see with you? Thank you so much young man, I'm not sure how I could ever repay you!' ");
                    pressEnter();
                    System.out.print("LADY: 'Please, take this for your journey. It should open the door to the CLOUD CASTLE.' ");
                    pressEnter();
                     System.out.println("\n\nCASTLE KEY OBTAINED!\n\n");
                     pressEnter();
                     //key added to inventory.
                     hasKey=1;
                }
            }
            } else {
                //When you have the key, talking to her will result in this.
                System.out.print("\nLADY: 'Thank you again for bringing back Turters! Please be safe on your journey.' ");
                pressEnter();
            }
            } 
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }  
        }

        if(roomNum==9){
            System.out.println("\nYou're walking amongst a path of clouds, and see a sign planted in the ground.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            //observing here will let you read the sign.
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("The Sign Reads: 'Solve this Maze to Reach the Castle!' ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=5;
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=21;
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=10;
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }

        }

        //The following rooms are all very similar, so there won't be any new comments until room 22.

        if(roomNum==10){
            System.out.println("\nYou're walking amongst a maze of clouds.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("It seems like you can only move to the north or east. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=11;
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=9;
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The clouds are too thick here.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }

        }
        if(roomNum==11){
            System.out.println("\nYou're walking amongst a maze of clouds.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("It seems like you can only move to the east or west. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=12;
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=10;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The clouds are too thick here.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }

        }
        if(roomNum==12){
            System.out.println("\nYou're walking amongst a maze of clouds.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("It seems like you can't move to the south. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=13;
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=11;
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=15;
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The clouds are too thick here.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }

        }
        if(roomNum==13){
            System.out.println("\nYou're walking amongst a maze of clouds.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("It seems like you can only move to the north or west. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=12;
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=14;
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The clouds are too thick here.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }

        }
        if(roomNum==14){
            System.out.println("\nYou're walking amongst a maze of clouds.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("It seems like you can't move to the north. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=16;
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=15;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=13;
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }

        }
        if(roomNum==15){
            System.out.println("\nYou're walking amongst a maze of clouds.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("It seems like you can't move to the west. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=14;
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=19;
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=12;
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }

        }
        if(roomNum==16){
            System.out.println("\nYou're walking amongst a maze of clouds.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.println("It seems like you can only move to the north or west. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=14;
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=17;
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The clouds are too thick here.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }

        }
        if(roomNum==17){
            System.out.println("\nYou're walking amongst a maze of clouds, and see a castle to the north!\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("It seems like you can't move to the east. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=18;
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=22;
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=16;
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }

        }
        if(roomNum==18){
            System.out.println("\nYou're walking amongst a maze of clouds.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("It seems like you can only move to the east or west. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=17;
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=19;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The clouds are too thick here.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }

        }
        if(roomNum==19){
            System.out.println("\nYou're walking amongst a maze of clouds.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("It seems like you can't move to the north.");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=18;
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=20;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=15;
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }

        }
        if(roomNum==20){
            System.out.println("\nYou're walking amongst a maze of clouds.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("It seems like you can only move to the east or west. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=19;
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=21;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The clouds are too thick here.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }

        }
        if(roomNum==21){
            System.out.println("\nYou're walking amongst a maze of clouds.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("It seems like you can only move to the east or south. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=20;
                }
                if(direction.equalsIgnoreCase("west")){
                     System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=9;
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }

        }

        if(roomNum==22){
            //Key from the Cloud Condo needs to be possessed in order to open the door.
            if(doorUnlocked==0){
                System.out.println("\nAs you approach the castle, you face a giant door that needs a key to open.\n");
                System.out.println("Would you like to move, open the menu, observe the area, or unlock the door?");
            } else {
                System.out.println("\nYou approach the castle, now unlocked.\n");
                System.out.println("Would you like to move, open the menu, or observe the area?");
                
            }
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("It looks like all you can do here is unlock the door or go back. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                   System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("north")){
                    if(doorUnlocked==1){
                        roomActive=23;
                        System.out.println("\n************************\n******CLOUD CASTLE******\n************************\n");
                    } else {
                        System.out.println("You have to unlock the door before you can enter the castle.");
                    }

                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=17;
                }
                
            }
            if(choice.equalsIgnoreCase("unlock")||choice.equalsIgnoreCase("unlock the door")){
                if(hasKey==1){
                doorUnlocked=1;
                System.out.print("You can now head north to enter the Castle! ");
                pressEnter();
                } else {
                    System.out.print("You need a key to unlock this door! ");
                    pressEnter();
                }
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        //Now you're inside the castle
        if(roomNum==23){
            System.out.println("\nNow inside the castle, it's time to explore around.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                //the rooms to the east and west have required items to open the bookshelf up north.
                System.out.print("You see rooms to the east and to the west, and stairs to a large bookshelf to the north. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=24;
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=25;
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=26;
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=22;
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
        }
        //the east wing
        if(roomNum==24){
            //gives a hint that you need to observe
            System.out.println("\nYou enter the east wing of the castle. It might be best to look around for clues!\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            //Observing starts the loop which gets you the princess book, and after you have the book, your observation about the room will change.
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                if(hasBookP==0){
                String takeBookP = new String();
                System.out.println("You see a bed, and a book lying on top of it. The book is called, 'The Princess'. \nWould you like to take the book? (Yes or No)");
                takeBookP=keyboard.next();
                //The player has the freedom to choose no and take the book later.
                if(takeBookP.equalsIgnoreCase("yes")){
                    System.out.println("\n\n'THE PRINCESS' BOOK OBTAINED!\n\n");
                    pressEnter();
                    //Princess Book added to your inventory
                    hasBookP=1;
                } else {
                    System.out.print("You left the book on the bed. ");
                    pressEnter();
                }
                } else {
                    System.out.print("It appears to be the empty bedroom of a princess. ");
                    pressEnter();
                }

            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The only thing east is the wall!");
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=23;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The only thing north is the wall!");
                }
                if(direction.equalsIgnoreCase("south")){
                   System.out.println("The only thing south is the wall!");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
        }
        //the west wing
        if(roomNum==25){
            System.out.println("\nYou enter the west wing of the castle. It might be best to look around for clues!\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            //Observing starts the loop which gets you the king book, and after you have the book, your observation about the room will change.
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                if(hasBookK==0){
                String takeBookK = new String();
                System.out.println("You see a bed, and a book lying on top of it. The book is called, 'The King'. \nWould you like to take the book? (Yes or No)");
                takeBookK=keyboard.next();
                if(takeBookK.equalsIgnoreCase("yes")){
                    System.out.println("\n\n'THE KING' BOOK OBTAINED!\n\n");
                    pressEnter();
                    //King Book added to your inventory
                    hasBookK=1;
                } else {
                    System.out.print("You left the book on the bed. ");
                    pressEnter();
                }
                } else {
                    System.out.print("It appears to be the empty bedroom of a king. ");
                    pressEnter();
                }

            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=23;
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The only thing west is the wall!");
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The only thing north is the wall!");
                }
                if(direction.equalsIgnoreCase("south")){
                   System.out.println("The only thing south is the wall!");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
        }
        //bookshelf room
        if(roomNum==26){
            //Made sure to have different messages based on if the bookshelf was already moved aside.
            if(bookshelfOpened==0){
                System.out.println("\nAs you approach the bookshelf on the top of the stairs, you notice two books are missing from the rest.\n");
                System.out.println("Would you like to move, open the menu, observe the area, or place books in the shelf?");
            } else {
                System.out.println("The bookshelf has moved aside, revealing a secret passage to the north.");
                System.out.println("Would you like to move, open the menu, or observe the area?");
            }
            
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                if(bookshelfOpened==0){
                    System.out.print("It looks like all you can do here is place books in the bookshelf or go back. ");
                } else {
                    System.out.print("It looks like all you can do here is go through the secret passage to the north, or go back. ");
                }
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                   System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The clouds are too thick here.");
                }
                if(direction.equalsIgnoreCase("north")){
                    if(bookshelfOpened==1){
                        roomActive=27;
                    } else {
                        System.out.println("It seems that the bookshelf is in the way of something... What could it be?");
                    }

                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=23;
                }
                
            }
            if(bookshelfOpened==0){
            if(choice.equalsIgnoreCase("place")||choice.equalsIgnoreCase("place books in the shelf")||choice.equalsIgnoreCase("place books")){
                //4 different possibilities based on having no books, one book, or both books.
                if(hasBookP==0&&hasBookK==0){
                    System.out.println("You have no books to place!");
                } else if(hasBookP==1&&hasBookK==0){
                    System.out.println("It looks like 'The Princess' fits in the shelf, but there's still one book empty spot left.");
                } else if(hasBookP==0&&hasBookK==1){
                    System.out.println("It looks like 'The King' fits in the shelf, but there's still one book empty spot left.");
                } else {
                    System.out.println("You were able to fit both 'The King' and 'The Princess' into the shelf... \nand suddenly, the bookshelf moves over, revealing a new room to the north to enter.");
                    bookshelfOpened=1;

                }
                
            }
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        //throne room
        if(roomNum==27){
            //This is the last room of the cloud kingdom.
            System.out.println("\nPast the bookshelf, you see something glowing.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                String takeOrb = new String();
                System.out.println("You see a pedastal, and an orb lying on top of it. It seems to have a blue glow around it. \nWould you like to take the orb? (Yes or No)");
                takeOrb=keyboard.next();
                if(takeOrb.equalsIgnoreCase("yes")){
                    //if you take orb, it's added to your inventory and the castle comes crashing down.
                    System.out.println("\n\nMYSTERIOUS BLUE ORB OBTAINED!\n\n");
                    pressEnter();
                    //Blue orb added to inventory
                    hasBlueOrb=1;
                    //Big dialogue sequence, but to sum it up, you get sent back down to the traveler's guild and find out that the old man and the lady were king and princess of the cloud kingdom.
                    System.out.println("The ground starts shaking arond you... and the entire castle falls to the ground!");
                    System.out.print("You seemingly passed out and appear to be back in the Traveler's Guild.\nYou see someone looking down at you... or wait, are there two people? ");
                    pressEnter();
                    System.out.print("LADY: 'Oh my, are you okay? It's been hours since we found you, and you're finally awake!' ");
                    pressEnter();
                    System.out.print("OLD MAN: 'Quiet down! Leave it to my daughter to cause a scene...' ");
                    pressEnter();
                    System.out.print("Daughter? You wonder who that could be referring to... and suddenly, you realize who it might be. ");
                    pressEnter();
                    System.out.print("LADY: 'This old man here is my father, the King of Cloud Kingdom. \nA few months ago, we lived together in the castle you were just in.' ");
                    pressEnter();
                    System.out.print("OLD MAN: 'But then... things changed. \nWe got into an argument, and before we could even apologize, we took our belongings and left each other.' ");
                    pressEnter();
                    System.out.print("LADY: 'And with those belongings... my pet turtle Turters, ended up with my father.' ");
                    pressEnter();
                    System.out.print("OLD MAN: 'Well, I couldn't just go over and give it back! You knew how things were...' ");
                    pressEnter();
                    System.out.print("LADY: 'Well, I guess you do have a point... Regardless, traveler, we require your help.' ");
                    pressEnter();
                    System.out.print("OLD MAN: 'It was our duty to protect the blue orb you found, but now with the castle destroyed, the orb is no longer safe.' ");
                    pressEnter();
                    System.out.print("LADY: 'There is another orb, one of a red glow, and it's said to be found in the depths of the kingdom.' ");
                    pressEnter();
                    System.out.print("OLD MAN: 'With both orbs together,  it's said that one wish of any kind can be granted.' ");
                    pressEnter();
                    System.out.print("You hear footsteps approaching you. It's the gnome from when you first came to the Traveler's Guild! ");
                    pressEnter();
                    System.out.println("GNOME: 'The King is right. Exit the guild and go west towards the depths. Once you have both orbs, come talk to me again.'");
                    System.out.print("It seems like your journey isn't over quite yet! ");
                    pressEnter();
                    roomActive=2;
                    System.out.println("\n************************\n****TRAVELER'S GUILD****\n************************\n");
                } else {
                    System.out.println("\nYou left the orb on the bed.\n");
                }

            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=23;
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The only thing west is the wall!");
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The only thing north is the wall!");
                }
                if(direction.equalsIgnoreCase("south")){
                   System.out.println("The only thing south is the wall!");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
        }
        //the cave to the west of the starting point.
        if(roomNum==28){
            //If you have the red orb, it means you already got the treasure inside the cave, so you're unable to go back
            if(hasRedOrb==0){
            //If you don't have the blue orb, you're unable to enter the cave. You need the blue orb to activate the statue that opens the tunnel
            if(hasBlueOrb==0){
            System.out.println("\nYou approach a cave protected by some sort of statue. \nIt looks like there's a spherical hole that something could fit in... I wonder what it could be?.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            } else {
            System.out.println("\nYou approach a cave protected by some sort of statue. \nThe blue orb in your pocket seems to be glowing, so you hold it up to the statue. A tunnel has revealed itself to the depths below.\n");
            System.out.println("Would you like to move, open the menu, observe the area, or descend the tunnel?");
            }
            } else {
                System.out.println("You approach the rubble of the cave you once went in.");
                System.out.println("Would you like to move, open the menu, or observe the area?");
            }
            choice=keyboard.next();
            //observation will change depending on your progress
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                if(hasRedOrb==1){
                    System.out.print("It appears as if the cave you once entered has crumbled down. ");
                    pressEnter();
                } else if(hasBlueOrb==0){
                System.out.print("The cave seems to need something to open up. All you can do is go back east. "); 
                pressEnter();   
                } else {
                System.out.print("It looks like all you can do here is descend into the depths of the cave or go back east. ");  
                pressEnter();  
                }
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=1;
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("Large trees block you from moving in that direction.");
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("It seems like the only way into the cave is down.");
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("Large trees block you from moving in that direction.");
                }
                
            }
            //Had to make sure descending is only possible if someone already cleared the cloud kingdom and didn't already clear the depths.
            if(hasRedOrb==0&&hasBlueOrb==1){
            if(choice.equalsIgnoreCase("descend")||choice.equalsIgnoreCase("descend the tunnel")){
                System.out.println("\n*******************\n****LAVA DEPTHS****\n*******************\n");
                roomActive=29;
            }
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        //Start of the underground, not much to comment on
        if(roomNum==29){
            System.out.println("\nWelcome to the underground! You've arrived in the Lava Depths! \nA path between lava lies in front of you.\n");
            System.out.println("Would you like to move, open the menu, observe the area, or ascend the tunnel?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("With lava surrounding you, it looks like there's a clear path north! ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=30;
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The lava surrounds that direction.");
                }
                
            }
            if(choice.equalsIgnoreCase("ascend")||choice.equalsIgnoreCase("ascend the tunnel")){
                roomActive=28;
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        if(roomNum==30){
            System.out.println("\nYou're walking in between a pathway of lava. You notice a sign in front of you.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            //Sign can be interacted with when observed. Next comments will be at room 34
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.println("The sign reads: \n'To those hoping to obtain the red orb, know that you will have to reach the end of this maze. \nTake advantage of what you find along the way.'\nWith lava surrounding you, it looks like there's a clear path east!");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=31;
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=29;
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        if(roomNum==31){
            System.out.println("\nYou're walking in between a pathway of lava.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("With lava surrounding you, it looks like there's a clear path north! ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=30;
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=32;
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The lava surrounds that direction.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        if(roomNum==32){
            System.out.println("\nYou're walking in between a pathway of lava.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("With lava surrounding you, it looks like there's a clear path west! ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=33;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=31;
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        if(roomNum==33){
            System.out.println("\nYou're walking in between a pathway of lava.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("With lava surrounding you, it looks like there's a clear path north! ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=32;
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=34;
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The lava surrounds that direction.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        //Room 34 branches between a path to the west and a path to the east. You have to go to the east path to get a torch that burns down tree roots on the west path. Once those are burned down, you can get the red orb and ender the endgame. Next comments at room 38
        if(roomNum==34){
            System.out.println("\nYou're walking in between a pathway of lava.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("With lava surrounding you, it looks like you can move any direction but north. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=35;
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=39;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=33;
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        if(roomNum==35){
            System.out.println("\nYou're walking in between a pathway of lava.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("With lava surrounding you, it looks like you can move either east or west. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=36;
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=34;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The lava surrounds that direction.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        if(roomNum==36){
            System.out.println("\nYou're walking in between a pathway of lava.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("With lava surrounding you, it looks like you can move either east or west. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=37;
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=35;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The lava surrounds that direction.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        if(roomNum==37){
            System.out.println("\nYou're walking in between a pathway of lava.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("With lava surrounding you, it looks like you can move either east or west. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=38;
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=36;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The lava surrounds that direction.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        //Torch Room
        if(roomNum==38){
            //checks to see if torch has already been claimed
            if(hasTorch==0){
            System.out.println("\nYou're walking in between a pathway of lava, and you approach a dead end with something bright lighting the way.\n");
            }
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            //have to observe to find the torch, since hint is given previously that you see something bright. Next comments at room 43
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                if(hasTorch==0){
                System.out.println("You see that a torch is hanging on the wall. Would you like to take it? (Yes/No)");
                String takeTorch = new String();
                takeTorch = scanner.next();
                if(takeTorch.equalsIgnoreCase("yes")){
                    System.out.println("\n\nTORCH OBTAINED!\n\n");
                    //Torch added to inventory
                    hasTorch=1;
                    pressEnter();
                } else {
                    System.out.print("You left the torch on the wall. ");
                    pressEnter();
                }
                } 
                System.out.print("With lava surrounding you, it looks like you can only move back west." );
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=37;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The lava surrounds that direction.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        if(roomNum==39){
            System.out.println("\nYou're walking in between a pathway of lava.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("With lava surrounding you, it looks like you can move north or east. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=34;
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=40;
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The lava surrounds that direction.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        if(roomNum==40){
            System.out.println("\nYou're walking in between a pathway of lava.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("With lava surrounding you, it looks like you can move either east or south. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=41;
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=39;
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        if(roomNum==41){
            System.out.println("\nYou're walking in between a pathway of lava.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("With lava surrounding you, it looks like you can move either east or west. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=42;
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=40;
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The lava surrounds that direction.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        if(roomNum==42){
            System.out.println("\nYou're walking in between a pathway of lava, and see some sort of object to your north.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                System.out.print("With lava surrounding you, it looks like you can move either north or west. ");
                pressEnter();
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("west")){
                    roomActive=41;
                }
                if(direction.equalsIgnoreCase("north")){
                    roomActive=43;
                }
                if(direction.equalsIgnoreCase("south")){
                    System.out.println("The lava surrounds that direction.");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        //Roots room
        if(roomNum==43){
            //need the torch to burn down the roots to access the next room. If you don't have the torch, you'll be turned away.
            if(hasTorch==0){
            System.out.println("\nYou approach a row of tree roots blocking you from the next room.\nCould there be a way to burn them down?\n");
            } else if(hasTorch==1) {
            System.out.println("\nYou approach a row of tree roots blocking you from the next room. You pull out your torch, and burn the roots down.\n");
            //I didn't want to add another variable for controlling whether the roots have been burned down by the player, so I just made it part of hasTorch. When hasTorch is 2, the roots have been burned down.
            hasTorch=2;
            } else {
                System.out.println("With the roots burned down, you can move safely into the dungeon ahead.");
            }
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            //observe message changes depending on if the roots have been burned down
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                if(hasTorch!=2){
                System.out.print("The roots look like they can be burned down with a torch! ");
                pressEnter();    
                } else {
                System.out.print("The dungeon of the depths lies ahead! ");    
                pressEnter();
                }
            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    System.out.println("The lava surrounds that direction.");
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The lava surrounds that direction.");
                }
                //Can only move forward if roots are burned down
                if(direction.equalsIgnoreCase("north")){
                    if(hasTorch==2){
                        roomActive=44;
                        System.out.println("\n*******************************\n*****DUNGEON OF THE DEPTHS*****\n*******************************\n");
                    } else {
                        System.out.println("You need to burn down the roots to move north!");
                    }
                }
                if(direction.equalsIgnoreCase("south")){
                    roomActive=42;
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
            
            
        }
        //Dungeon of the depths
        if(roomNum==44){
            System.out.println("\nNow past the roots, you see something glowing.\n");
            System.out.println("Would you like to move, open the menu, or observe the area?");
            choice=keyboard.next();
            //Have to observe to take the red orb.
            if(choice.equalsIgnoreCase("observe")||choice.equalsIgnoreCase("observe the area")){
                String takeOrb = new String();
                System.out.println("You see a pedastal, and an orb lying on top of it. It seems to have a red glow around it. \nWould you like to take the orb? (Yes or No)");
                takeOrb=keyboard.next();
                if(takeOrb.equalsIgnoreCase("yes")){
                    System.out.println("\n\nMYSTERIOUS RED ORB OBTAINED!\n\n");
                    pressEnter();
                    //Red Orb added to inventory, and a text sequence starts that puts you back in traveler's guild. From there, the game is able to be won.
                    hasRedOrb=1;
                    System.out.println("The ground starts shaking arond you... and the rocks above you start to separate!\nYou can see the lonely gray sky above you.");
                    pressEnter();
                    System.out.print("LADY: 'Traveler! You're okay! So you were actually able to obtain the red orb?' ");
                    pressEnter();
                    System.out.print("OLD MAN: 'Atta boy, I knew you could do it! Let's get you that wish!' ");
                    pressEnter();
                    roomActive=2;
                    System.out.println("\n************************\n****TRAVELER'S GUILD****\n************************\n");

                } else {
                    System.out.print("You left the orb on the bed. ");
                    pressEnter();
                }

            }
            if(choice.equalsIgnoreCase("move")){
                System.out.println("Where would you like to move?");
                direction=keyboard.next();
                if(direction.equalsIgnoreCase("east")){
                    roomActive=23;
                }
                if(direction.equalsIgnoreCase("west")){
                    System.out.println("The only thing west is the wall!");
                }
                if(direction.equalsIgnoreCase("north")){
                    System.out.println("The only thing north is the wall!");
                }
                if(direction.equalsIgnoreCase("south")){
                   System.out.println("The only thing south is the wall!");
                }
                
            }
            if(choice.equalsIgnoreCase("menu")||choice.equalsIgnoreCase("open the menu")||choice.equalsIgnoreCase("open")){
                menu();
            }
        }
        
            
    }
    //Helps pause dialogue by asking the player to manually continue the story.
    public static void pressEnter(){
        System.out.println("(Press \"ENTER\" to continue...)");
        Scanner scanner2 = new Scanner(System.in);
        scanner2.nextLine();
    }
    //loads the manual save file
    public static void load(){
    try {
      // Creates a FileReader
      FileReader file = new FileReader("savedataTexter.txt");

      // Creates a BufferedReader
      BufferedReader input = new BufferedReader(file);

      // Reads characters
      String inputedData = input.readLine();
      //System.out.println("Data in the file: ");
      String[] inputedDataAsArray = inputedData.split(",");



      //System.out.println(inputedDataAsArray[0] + "!!!!!!");
      //System.out.println(inputedDataAsArray[1]  + "!!!!!!");
      //System.out.println(Arrays.toString(inputedDataAsArray));

    roomActive = Integer.parseInt(inputedDataAsArray[0]);
    hasSword = Integer.parseInt(inputedDataAsArray[1]);
    sealBroken = Integer.parseInt(inputedDataAsArray[2]);
    hasTurtle = Integer.parseInt(inputedDataAsArray[3]);
    hasKey = Integer.parseInt(inputedDataAsArray[4]);
    doorUnlocked = Integer.parseInt(inputedDataAsArray[5]);
    hasBookP = Integer.parseInt(inputedDataAsArray[6]);
    hasBookK = Integer.parseInt(inputedDataAsArray[7]);
    bookshelfOpened = Integer.parseInt(inputedDataAsArray[8]);
    hasBlueOrb = Integer.parseInt(inputedDataAsArray[9]);
    hasRedOrb = Integer.parseInt(inputedDataAsArray[10]);
    hasTorch = Integer.parseInt(inputedDataAsArray[11]);
      // Closes the reader
      input.close();
    }
    catch(Exception e) {
        System.out.println("####");
      e.getStackTrace();
    }
    //loads the autosave file
    }
    public static void loadAuto(){
    try {
      // Creates a FileReader
      FileReader file = new FileReader("autosavedataTexter.txt");

      // Creates a BufferedReader
      BufferedReader input = new BufferedReader(file);

      // Reads characters
      String inputedData = input.readLine();
      //System.out.println("Data in the file: ");
      String[] inputedDataAsArray = inputedData.split(",");



      //System.out.println(inputedDataAsArray[0] + "!!!!!!");
      //System.out.println(inputedDataAsArray[1]  + "!!!!!!");
      //System.out.println(Arrays.toString(inputedDataAsArray));

    roomActive = Integer.parseInt(inputedDataAsArray[0]);
    hasSword = Integer.parseInt(inputedDataAsArray[1]);
    sealBroken = Integer.parseInt(inputedDataAsArray[2]);
    hasTurtle = Integer.parseInt(inputedDataAsArray[3]);
    hasKey = Integer.parseInt(inputedDataAsArray[4]);
    doorUnlocked = Integer.parseInt(inputedDataAsArray[5]);
    hasBookP = Integer.parseInt(inputedDataAsArray[6]);
    hasBookK = Integer.parseInt(inputedDataAsArray[7]);
    bookshelfOpened = Integer.parseInt(inputedDataAsArray[8]);
    hasBlueOrb = Integer.parseInt(inputedDataAsArray[9]);
    hasRedOrb = Integer.parseInt(inputedDataAsArray[10]);
    hasTorch = Integer.parseInt(inputedDataAsArray[11]);
      // Closes the reader
      input.close();
    }
    catch(Exception e) {
        System.out.println("####");
      e.getStackTrace();
    }
    }

    //handles manual saving
    public static void save(){
    try {
    BufferedWriter writer = new BufferedWriter(new FileWriter("savedataTexter.txt"));
        writer.write(roomActive+","+hasSword+","+sealBroken+","+hasTurtle+","+hasKey+","+doorUnlocked+","+hasBookP+","+hasBookK+","+bookshelfOpened+","+hasBlueOrb+","+hasRedOrb+","+hasTorch+",");
        writer.close();
    }
    catch(Exception e) {
        System.out.println("####");
      e.getStackTrace();
    }
    }
    //handles autosaving
    public static void autosave(){
    try {
    BufferedWriter writer = new BufferedWriter(new FileWriter("autosavedataTexter.txt"));
        writer.write(roomActive+","+hasSword+","+sealBroken+","+hasTurtle+","+hasKey+","+doorUnlocked+","+hasBookP+","+hasBookK+","+bookshelfOpened+","+hasBlueOrb+","+hasRedOrb+","+hasTorch+",");
        writer.close();
    }
    catch(Exception e) {
        System.out.println("####");
      e.getStackTrace();
    }
    }
    //handles the menu system
    public static void menu(){
        String menuChoice = new String();
        System.out.println("MENU OPTIONS:\n[a] Check Inventory\n[b] Save Game\nChoose any other key to go back.");
        menuChoice=scanner.next();
        if(menuChoice.equalsIgnoreCase("a")){
            //inventory will check for the variable values of every item.
            System.out.println("\nInventory:");
            if(hasSword==1){
                System.out.println("Sword (x1)");
            }
            //You won't have the turtle anymore after you give it to the lady
            if(hasTurtle==1&&hasKey==0){
                System.out.println("Turtle (x1)");
            }
            if(hasKey==1){
                System.out.println("Key (x1)");
            }
            //you won't have the books anymore when you put them in the bookshelf
            if(hasBookP==1&&bookshelfOpened==0){
                System.out.println("'The Princess Book' (x1)");
            }
            if(hasBookK==1&&bookshelfOpened==0){
                System.out.println("'The King Book' (x1)");
            }
            if(hasBlueOrb==1){
                System.out.println("Mysterious Blue Orb (x1)");
            }
            if(hasRedOrb==1){
                System.out.println("Mysterious Red Orb (x1)");
            }
            if(hasTorch!=0){
                System.out.println("Torch (x1)");
            }
            System.out.println("");
        } else if(menuChoice.equalsIgnoreCase("b")){
            //triggers a manual save
            save();
        }
    }
}
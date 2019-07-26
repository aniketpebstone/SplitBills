package org.aniket.splitbills.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BusinessLogic {

    public List<Result> generateResult(List<Dues> dues)
    {

        List<Result> results=new ArrayList<>();
        List<Dues> positive=new ArrayList<>();
        List<Dues> negetive=new ArrayList<>();

        for(Dues due:dues)
        {
            if(due.difference > 0)
            {
                positive.add(due);
            }
            else if(due.difference < 0)
            {
                negetive.add(due);
            }
        }
        System.out.println("                  -----   positive  ------                  ");
        System.out.println(positive);
        System.out.println("                  -----   negetive  ------                  ");
        System.out.println(negetive);
        while(positive.size() !=0 && negetive.size()!=0)
        {
            if(positive.get(0).difference >  Math.abs(negetive.get(0).difference))
            {
                positive.get(0).difference-=Math.abs(negetive.get(0).difference);
                results.add(new Result(positive.get(0).personId,"",negetive.get(0).personId,"",Math.abs(negetive.get(0).difference)));
                negetive.remove(0);
            }
            else if(positive.get(0).difference < Math.abs(negetive.get(0).difference))
            {
                negetive.get(0).difference+=positive.get(0).difference;
                results.add(new Result(positive.get(0).personId,"",negetive.get(0).personId,"",positive.get(0).difference));
                positive.remove(0);
            }
            else
            {
                results.add(new Result(positive.get(0).personId,"",negetive.get(0).personId,"",positive.get(0).difference));
                positive.remove(0);
                negetive.remove(0);
            }
        }
        return results;
    }

    public List<Dues> generateDues(List<Transaction> transactions, List<Split> splits)
    {
        List<Dues> dues=new ArrayList<>();
        //For receivables calculation


        for(Transaction transaction:transactions)
        {
            Dues due=new Dues();
            due.personId=transaction.getPersonId();
            if(dues.contains(due))
            {
                Dues existDue=getDueFromList(dues,due);
                existDue.duesReceivable+=transaction.getAmount();
            }
            else
            {
                due.duesReceivable=transaction.getAmount();
                dues.add(due);
            }
        }

        for(Split split:splits)
        {
            Dues due=new Dues();
            due.personId=split.getSplitAmong();
            if(dues.contains(due))
            {
                Dues existDue=getDueFromList(dues,due);
                existDue.duesPayable+=split.getSplitAmount();
            }
            else
            {
                due.duesPayable=split.getSplitAmount();
                dues.add(due);
            }

        }

        for(Dues diffDue:dues)
        {
            diffDue.difference=diffDue.duesReceivable-diffDue.duesPayable;
        }
        return dues;
    }

    private Dues getDueFromList(List<Dues> dues,Dues due) {
       for(Dues d:dues)
       {
           if(d.equals(due))
           {
               return d;
           }
       }
    return null;
    }

    public static void main(String args[])
    {
        List<Transaction> txnList=new ArrayList<>();
        txnList.add(new Transaction(1,1,"",50,new Date().getTime()));
        txnList.add(new Transaction(1,1,"",20,new Date().getTime()));
        txnList.add(new Transaction(1,2,"",30,new Date().getTime()));
        txnList.add(new Transaction(1,1,"",40,new Date().getTime()));
        txnList.add(new Transaction(1,4,"",20,new Date().getTime()));
        txnList.add(new Transaction(1,1,"",10,new Date().getTime()));
        txnList.add(new Transaction(1,2,"",10,new Date().getTime()));
        txnList.add(new Transaction(1,3,"",40,new Date().getTime()));


        List<Split> splitsList=new ArrayList<>();
        splitsList.add(new Split(1,1,1,3,25));
        splitsList.add(new Split(1,1,1,4,25));
        splitsList.add(new Split(1,2,1,1,10));
        splitsList.add(new Split(1,2,1,2,10));
        splitsList.add(new Split(1,3,2,1,10));
        splitsList.add(new Split(1,3,2,2,10));
        splitsList.add(new Split(1,3,2,3,10));
        splitsList.add(new Split(1,4,1,1,20));
        splitsList.add(new Split(1,4,1,4,20));
        splitsList.add(new Split(1,5,4,2,10));
        splitsList.add(new Split(1,5,4,3,10));
        splitsList.add(new Split(1,6,1,1,10));
        splitsList.add(new Split(1,7,2,4,10));
        splitsList.add(new Split(1,8,3,1,20));
        splitsList.add(new Split(1,8,3,3,20));


        List<Dues> dues=new BusinessLogic().generateDues(txnList,splitsList);
        System.out.println("=======================   DUES  ==============================");
        System.out.println(dues);

        List<Result> results=new BusinessLogic().generateResult(dues);
        System.out.println("=======================   RESULT  ==============================");
        System.out.println(results);

    }

}

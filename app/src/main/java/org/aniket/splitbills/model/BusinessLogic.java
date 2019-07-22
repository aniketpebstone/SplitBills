package org.aniket.splitbills.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessLogic {

    public List<Result> generateResult(List<Dues> dues)
    {

        return  null;
    }

    public List<Dues> generateDues(List<Transaction> transactions, List<Split> splits)
    {
        List<Dues> dues=new ArrayList<>();

        //For receivables calculation
        Map<Integer,Float> map=new HashMap<>();

        for(Transaction transaction:transactions)
        {
            if(map.containsKey(transaction.getPersonId()))
            {
                float val=map.get(transaction.getPersonId());
                val+=transaction.getAmount();
                map.put(transaction.getPersonId(),val);
            }
            else
            {
                map.put(transaction.getPersonId(),transaction.getAmount());
            }
        }

        for (Map.Entry<Integer,Float> entry : map.entrySet())
        {
            Dues due=new Dues();
            due.personId=entry.getKey();
            due.duesReceivable=entry.getValue();
            dues.add(due);
        }

        //For payable calculation
        map=new HashMap<>();
        for(Split split:splits)
        {
            if(map.containsKey(split.getSplitAmong()))
            {
                float val=map.get(split.getSplitAmong());
                val+=split.getSplitAmount();
                map.put(split.getSplitAmong(),val);
            }
            else
            {
                map.put(split.getSplitAmong(),split.getSplitAmount());
            }
        }

        for (Map.Entry<Integer,Float> entry : map.entrySet())
        {
            for(Dues due:dues)
            {
                if(due.personId==entry.getKey())
                {
                    due.duesPayable=entry.getValue();
                    due.difference=due.duesReceivable-due.duesPayable;
                }
            }
        }
        return dues;
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

        System.out.println(new BusinessLogic().generateDues(txnList,splitsList));

    }

}

package linked.doublelined;

public class DoubleLinkedLineDemo {
    public static void main(String[] args) {
        DoubleLInkedLine db = new DoubleLInkedLine();
        DoubleLInkedLine.FamilyNode fa1 = db.createFamilyMember(1, "Jake", "husbandman");
        DoubleLInkedLine.FamilyNode fa2 = db.createFamilyMember(2, "Zoe", "beauty");
        DoubleLInkedLine.FamilyNode fa4 = db.createFamilyMember(4, "Guan", "mama");
        DoubleLInkedLine.FamilyNode fa3 = db.createFamilyMember(3, "Hou", "papa");
        DoubleLInkedLine.FamilyNode fa5 = db.createFamilyMember(3, "Hou", "father");

        db.addAsOrder(fa4);
        db.addAsOrder(fa1);
        db.addAsOrder(fa2);
        db.addAsOrder(fa3);

        db.update(fa5);


        db.listAll();

    }
}

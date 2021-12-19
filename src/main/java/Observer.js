/* Behavioral Design Pattern – Observer

interface IPublisher is
    method addSubscriber(ISubscriber)
    method deleteSubscriber(ISubscriber)
    method notifySubscribers()

interface ISubscriber is
    method update(float costPerMember)


class GroupExpense implements IPublisher is
    int id
    int idExpense
    float costPerMember
    int numberMembers
    float priceExpense
    List<User> groupMembers

    constructor GroupExpense(id, idExpense) is
        id = id
        idExpense = idExpense

    method notifySubscribers() is
        for subscriber in groupMembers
            subscriber.update(costPerMember)

    method addSubscriber(ISubscriber) is
        groupMembers.add(ISubscriber)
        numberMembers += 1
        costPerMember = priceExpense/numberMembers

    method deleteSubscriber(ISubscriber) is
        groupMembers.delete(ISubscriber)
        numberMembers -=1
        costPerMember = priceExpense/numberMembers


class User implements ISubscriber is
    int id
    string name
    string email
    string password

    constructor User(id, name, email, password) is
        id = id;
        name = name;
        email = email;
        password = password;

    method update (float costPerMember) is
        console.log("You have to pay" + costPerMember);
*/
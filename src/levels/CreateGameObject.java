package levels;

public class CreateGameObject {
	public GameObject GenerateGerneralObject(char c) // Generate a new game object from char
	{
		switch(c){
		case '#':
			return new Wall();
		case '@':
			return new Box();
		case 'A':
			return new Character();
		case 'o':
			return new Target();
		case ' ':
			return null;
		default: return null;
		}

		
		
	}
}

package ex2.ex2;

import ex2.geo.*;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;
import ex2.gui.StdDraw_Ex2;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class represents a collection of GUI_Shape.
 * Ex2: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements GUI_Shape_Collection {
	// instance data members:
	private ArrayList<GUI_Shape> _shapes;
	// constructor who create new ShapeCollection:
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}
	// copy constructor get other ShapeCollection, create is copy
	public ShapeCollection(ShapeCollection SH) {
		_shapes = new ArrayList<GUI_Shape>();
		for (int i=0 ; i<SH.size() ; i++){
			this._shapes.add(i,SH._shapes.get(i));
		}
	}
	/**
	 * This method return a GUI_Shape to the i'th element in the collection.
	 * @param i - the index of the element
	 * @return a GUI_Shape (NOT a copy) for the i'th element in the collection.
	 */
	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}
	/**
	 * return the size of the collection (if empty return 0).
	 * @return size
	 */
	@Override
	public int size() {
		return _shapes.size();
	}
	/**
	 * This method remove (and return) the GUI_Shape at the i'th location.
	 * After removing the size of this collection decreases (by 1) and the order (of the elements) remains the same - just with out the removed element.
	 * @param i - the index of the element to be removed.
	 * @return the GUI_Shape which was removed
	 */
	@Override
	public GUI_Shape removeElementAt(int i) {
		GUI_Shape s = _shapes.get(i);
		_shapes.remove(i);
		return s;
	}
	/**
	 * This method adds the GUI_Shape s to this collection in the i'th position.
	 * @param s - the GUI_Shape
	 * @param i - the location (index) in which s should be added
	 */
	@Override
	public void addAt(GUI_Shape s, int i) {
		if(s!=null && s.getShape()!=null && i>=0 && i <_shapes.size()) {
			_shapes.add(i, s);
		}
	}
	/**
	 * This method adds the GUI_Shape s to this collection in the last position.
	 * @param s - the GUI_Shape
	 */
	@Override
	public void add(GUI_Shape s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	/**
	 * This method constructs a deep copy of this collection.
	 * @return ShapeCollection copy
	 */
	@Override
	public GUI_Shape_Collection copy() {
		return new ShapeCollection(this);
	}
	/**
	 * This method sorts this ShapeCollection according to the comp Comparator - in increasing order.
	 * @param comp a linear order over ShapeCollection as defined in java.util.Comparator
	 */
	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		if (_shapes != null){
		_shapes.sort(comp);}
	}
	/**
	 * This method simple removes all the elements from this collection.
	 */
	@Override
	public void removeAll() {
		_shapes.clear();
	}
	/**
	 * This method saves this ShapeCollection to a text file.
	 * @param file - the file name in which this collection will be saved.
	 */
	@Override
	public void save(String file) {
		try {
			FileWriter Writer = new FileWriter(file);
			for (int i=0; i< _shapes.size(); i++){
				Writer.write(_shapes.get(i).toString()+"\n");
			}
			Writer.close();
		}
		catch (Exception e){
			System.out.println("An error has occurred.");
		}
	}
	/**
	 * This method restore a ShapeCollection from text file (as saved be the save method).
	 * @param file - the name of the text file to create a ShapeCollection file from.
	 */
	@Override
	public void load(String file) {
		_shapes.clear();
		try {
			File Obj = new File(file);
			Scanner scanner = new Scanner(Obj);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				GUIShape G = new GUIShape(line);
				_shapes.add(G);
			}
			scanner.close();
		}
		catch (Exception e) {
			System.out.println("An error has occurred.");
		}
	}
	/**
	 * This method returns a String representing this ShapeCollection,
	 * such that one can use this string for saving ShapeCollection into a text file.
	 * @return a String representing this ShapeCollection
	 */
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
	

}

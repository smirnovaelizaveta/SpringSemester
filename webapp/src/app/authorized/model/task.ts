import { Project } from './project';
import { Solution } from './solution';

export interface Task {
  id: number;
  name: string;
  description: string;
  difficultyLevel: number;
  solution?: Solution
}

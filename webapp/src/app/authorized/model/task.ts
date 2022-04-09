import { Project } from './project';
import { Solution } from './solution';

export interface Task {
  id: number;
  name: string;
  description: string;
  status: Status;
  difficultyLevel: number;
  solution?: Solution
}

export enum Status {
  SOLVED,
  IN_PROGRESS,
  NOT_STARTED
}
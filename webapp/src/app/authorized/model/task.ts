import { Project } from './project';

export interface Task {
  id: number;
  name: string;
  description: string;
  status: Status;
  difficultyLevel: number;
}

export enum Status {
  SOLVED,
  IN_PROGRESS,
  NOT_STARTED
}
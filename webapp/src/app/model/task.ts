import { Project } from './project';

export interface Task {
  id: string;
  name: string;
  description: string;
  status: Status;
  project: Project;
}

export enum Status {
  SOLVED,
  IN_PROGRESS,
  NOT_STARTED
}